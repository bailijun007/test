package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.redis.pubsub.vo.BBKLine;
import com.blj.redis.pubsub.vo.ExBbKlineVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author BaiLiJun  on 2020/3/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ZSetDemo2 {
    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Test
    public void save1() {
        final String key = "testlist:" + "BTC" + ":" + "BTC_USDT:1";
        ExBbKlineVo bbkLine = new ExBbKlineVo();
        Long minute = 1583723580000L;
        bbkLine.setTimestamp(minute);
        bbkLine.setHigh(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setLow(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setOpen(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setClose(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setVolume(new BigDecimal(0.019791000000000000000000000000 + ""));
        BigDecimal[] bigDecimals = new BigDecimal[6];
        bigDecimals[0] = new BigDecimal(minute);
        bigDecimals[1] = bbkLine.getHigh() == null ? BigDecimal.ZERO : bbkLine.getHigh().stripTrailingZeros();
        bigDecimals[2] = bbkLine.getLow() == null ? BigDecimal.ZERO : bbkLine.getLow().stripTrailingZeros();
        bigDecimals[3] = bbkLine.getOpen() == null ? BigDecimal.ZERO : bbkLine.getOpen().stripTrailingZeros();
        bigDecimals[4] = bbkLine.getClose() == null ? BigDecimal.ZERO : bbkLine.getClose().stripTrailingZeros();
        bigDecimals[5] = bbkLine.getVolume() == null ? BigDecimal.ZERO : bbkLine.getVolume().stripTrailingZeros();

        final Set<ZSetOperations.TypedTuple<String>> typedTuples = new HashSet<>();
        final double score = minute.doubleValue();
//        final String s = JsonUtil.toJsonString(bigDecimals);
//       templateDB0.opsForZSet().add(key, s, score);

//        System.out.println(templateDB0.opsForZSet().range(key, 0, -1));

    }

    @Test
    public void save() {

        final Set<Object> set = new HashSet<>();
        ExBbKlineVo bbkLine = new ExBbKlineVo();
        Long minute = 1583723580000L;
        bbkLine.setTimestamp(minute);
        bbkLine.setHigh(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setLow(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setOpen(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setClose(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setVolume(new BigDecimal(0.019791000000000000000000000000 + ""));

        final double score = minute.doubleValue();

        set.add(minute);
        set.add(bbkLine.getHigh());
        set.add(bbkLine.getLow());
        set.add(bbkLine.getOpen());
        set.add(bbkLine.getClose());
        set.add(bbkLine.getVolume());


        //向集合中插入元素，并设置分数
        final String key = "testlist:" + "BTC" + ":" + "BTC_USDT:1";
        templateDB0.opsForZSet().add(key, set.toString(), 1583723580000L);


        Set<ZSetOperations.TypedTuple<BigDecimal>> tuples = new HashSet<ZSetOperations.TypedTuple<BigDecimal>>();
//        tuples.add()

//        ZSetOperations.TypedTuple<String> objectTypedTuple1 = new DefaultTypedTuple<String>("zset-5",9.6);
//        ZSetOperations.TypedTuple<String> objectTypedTuple2 = new DefaultTypedTuple<String>("zset-6",9.9);
//        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<ZSetOperations.TypedTuple<String>>();
//        tuples.add(objectTypedTuple1);
//        tuples.add(objectTypedTuple2);
//        System.out.println(templateDB0.opsForZSet().add("zset1",tuples));
//        System.out.println(templateDB0.opsForZSet().range("zset1",0,-1));

    }

    @Test
    public void saveKline() {
        long minute = 26388150L;
        BBKLine bbkLine = new BBKLine();
        bbkLine.setAsset("BTC");
        bbkLine.setSymbol("BTC_USDT");
        bbkLine.setFrequence(5);
        bbkLine.setMinute(minute);
        bbkLine.setHigh(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setLow(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setOpen(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setClose(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setVolume(new BigDecimal(0.019791000000000000000000000000 + ""));
        //向集合中插入元素，并设置分数
        templateDB0.opsForZSet().add("kline:from_exp:repair:BB:" + "BTC" + ":" + "BTC_USDT:1", JSON.toJSONString(bbkLine), 26388150);
        final double minDouble = Long.valueOf(minute).doubleValue();
        Set<String> range = templateDB0.opsForZSet()
                .rangeByScore("kline:from_exp:repair:BB:" + "BTC" + ":" + "BTC_USDT:1", minDouble, minDouble + 5);

        List<BBKLine> list = new ArrayList<>();

        if (!range.isEmpty()) {
//            final String s = new ArrayList<>(range).get(0);
//            System.out.println("s = " + s);
////            JSON字符串转JSON对象
//            BBKLine bbkLine1 = JSON.parseObject(s,BBKLine.class);
//            System.out.println("bbkLine1 = " + bbkLine1);
//            System.out.println(bbkLine1.getAsset());

//            final ArrayList<String> list = new ArrayList<>(range);
//            for (String s : list) {
//             // JSON字符串转JSON对象
//            BBKLine bbkLine1 = JSON.parseObject(s,BBKLine.class);
//                System.out.println(bbkLine1);

            for (String s : range) {
                BBKLine bbkLine1 = JSON.parseObject(s, BBKLine.class);
                list.add(bbkLine1);
            }
        }
        for (BBKLine line : list) {
            System.out.println("line = " + line);
        }

    }


}


