package com.blj.redis.pubsub.kline;

import com.alibaba.fastjson.JSON;
import com.blj.redis.pubsub.vo.BBKLine;
import com.blj.redis.pubsub.vo.BBSymbol;
import com.blj.redis.pubsub.constant.BbKLineKey;
import com.blj.redis.pubsub.vo.BBKlineTrade;
import com.blj.redis.pubsub.vo.BbTradeVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun on 2020/3/4
 */
@Component
@Slf4j
public class BBKlineBuild {

    private static final Logger logger = LoggerFactory.getLogger(BBKlineBuild.class);

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Resource(name = "klineTemplateDB2")
    private StringRedisTemplate klineTemplateDB2;

    @Value("${bb.kline.ongoingCalc.enable}")
    private int ongoingCalcEnable;

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            1,
            1,
            0, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(10000000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    @Scheduled(cron = "*/1 * * * * *")
    public void bbKlineBuild() {
        if (1 != ongoingCalcEnable) {
            return;
        }
        threadPool.execute(() -> trigger());

    }


//    @PostConstruct
    public void trigger() {
        List<BBSymbol> bbSymbols = listSymbol();

        for (BBSymbol bbSymbol : bbSymbols) {
            String asset = bbSymbol.getAsset();
            String symbol = bbSymbol.getSymbol();
            String channel = BbKLineKey.BB_TRADE + ":" + asset + ":" + symbol;

            templateDB0.getConnectionFactory().getConnection().subscribe(new MessageListener() {
                @Override
                public void onMessage(Message message, byte[] pattern) {
                    String msg = new String(message.getBody());
                    logger.info("收到k线推送消息:{}" , msg);
                    List<BbTradeVo> list = listTrade(msg);


                    // 拆成不同的分钟
                    Map<Long, List<BbTradeVo>> minute2TradeList = list.stream()
                            .collect(Collectors.groupingBy(klineTrade -> TimeUnit.MILLISECONDS.toMinutes(klineTrade.getTradeTime())));

                    for (Long ms : minute2TradeList.keySet()) {
                        long minute = TimeUnit.MILLISECONDS.toMinutes(ms);
                        List<BbTradeVo> trades = minute2TradeList.get(ms);
                        BBKLine newkLine = buildKline(trades, asset, symbol, minute);

                        BBKLine oldkLine = getOldKLine(asset, symbol, minute, 1);

                        BBKLine mergedKline = merge(oldkLine, newkLine);

                        logger.info("合并k线数据为：[{}]" , mergedKline.toString());

                        saveKline(mergedKline, asset, symbol, minute, 1);

                        logger.info("保存k线数据成功");

                        notifyUpdate(asset, symbol, minute, 1);

                        logger.info("k线数据成功，发出通知！");
                    }
                }
            }, channel.getBytes());

        }


    }

    private BBKLine merge(BBKLine oldkLine, BBKLine newkLine) {
        // oldKline 有可能是空，直接返回newKline
        if (null == oldkLine) {
        } else {
            newkLine.setHigh(newkLine.getHigh().max(oldkLine.getHigh()));
            newkLine.setLow(newkLine.getLow().min(oldkLine.getLow()));
            newkLine.setOpen(oldkLine.getOpen());
            newkLine.setVolume(newkLine.getVolume().add(oldkLine.getVolume()));
        }
        return newkLine;
    }


    private List<BBSymbol> listSymbol() {
        HashOperations opsForHash = templateDB0.opsForHash();
        Cursor<Map.Entry<String, Object>> curosr = opsForHash.scan(BbKLineKey.BB_SYMBOL, ScanOptions.NONE);

        List<BBSymbol> list = new ArrayList<>();
        while (curosr.hasNext()) {
            Map.Entry<String, Object> entry = curosr.next();
            Object o = entry.getValue();
            BBSymbol bBSymbolVO = JSON.parseObject(o.toString(), BBSymbol.class);
            list.add(bBSymbolVO);
        }
        return list;
    }

    /*
     * kline:from_exp:repair:BB:${asset}:${symbol}:${minute}
     *   interval 频率；1分钟
     */
    private void saveKline(BBKLine kline, String asset, String symbol, long minute, int interval) {
        //向集合中插入元素，并设置分数
        String key = buildKlineSaveRedisKey(asset, symbol, interval);
        klineTemplateDB2.opsForZSet().removeRangeByScore(key, minute, minute);
        klineTemplateDB2.opsForZSet().add(key, JSON.toJSONString(kline), minute);
    }

    private void notifyUpdate(String asset, String symbol, long minute, int interval) {
        //向集合中插入元素，并设置分数
        String key = buildUpdateRedisKey(asset, symbol, interval);
        klineTemplateDB2.opsForZSet().add(key, asset + "#" + symbol + "#" + minute, minute);}

    private BBKLine buildKline(List<BbTradeVo> trades, String asset, String symbol, long minute) {
        BBKLine bBKLine = new BBKLine();
        bBKLine.setAsset(asset);
        bBKLine.setSymbol(symbol);
        bBKLine.setFrequence(1);
        bBKLine.setMinute(minute);

        BigDecimal highPrice = BigDecimal.ZERO;
        BigDecimal openPrice = null;
        BigDecimal closePrice = null;
        BigDecimal volume = BigDecimal.ZERO;

        for (BbTradeVo trade : trades) {
            BigDecimal currentPrice = trade.getPrice();
            highPrice = highPrice.compareTo(currentPrice) >= 0 ? highPrice : currentPrice;
            openPrice = null == openPrice ? currentPrice : openPrice;
            closePrice = currentPrice;
            volume = volume.add(trade.getNumber());
        }

        BigDecimal lowPrice = trades.stream().map(trade -> trade.getPrice()).min((t1, t2) -> t1.compareTo(t2)).get();

        bBKLine.setHigh(highPrice);
        bBKLine.setLow(lowPrice);
        bBKLine.setOpen(openPrice);
        bBKLine.setClose(closePrice);
        bBKLine.setVolume(volume);

        return bBKLine;
    }


    private List<BbTradeVo> listTrade(String msg) {
        BBKlineTrade bbKlineTrade = JSON.parseObject(msg, BBKlineTrade.class);
        return bbKlineTrade.getTrades();
    }


    BBKLine getOldKLine(String asset, String symbol, long minute, int interval) {
        BBKLine bbkLine1 = null;
        String key = buildKlineSaveRedisKey(asset, symbol, interval);
        Set<String> range = klineTemplateDB2.opsForZSet()
                .rangeByScore(key, minute, minute);

        if (!range.isEmpty()) {
            final String s = new ArrayList<>(range).get(0);
//            JSON字符串转JSON对象
            bbkLine1 = JSON.parseObject(s, BBKLine.class);
        }
        return bbkLine1;
    }

    private String buildUpdateRedisKey(String asset, String symbol, int interval) {
        return BbKLineKey.KLINE_BB_UPDATE + asset + ":" + symbol + ":" + interval;
    }

    private String buildKlineSaveRedisKey(String asset, String symbol, int interval) {
        return BbKLineKey.KLINE_BB + asset + ":" + symbol + ":" + interval;
    }
}
