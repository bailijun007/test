package com.blj.redis.pubsub.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blj.redis.pubsub.Pub;
import com.blj.redis.pubsub.vo.BBKLine;
import com.blj.redis.pubsub.constant.BbKLineKey;
import com.blj.redis.pubsub.kline.BBKlineBuild;
import lombok.val;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author BaiLiJun  on 2020/3/5
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Resource(name = "klineTemplateDB2")
    private StringRedisTemplate klineTemplateDB2;

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;


    @org.junit.Test
    public void test1() {
        final Set<Object> xbbb_symbol = klineTemplateDB2.opsForHash().keys("bb_symbol");
        System.out.println(xbbb_symbol.size());
        final Set<Object> bb_symbol = templateDB0.opsForHash().keys("bb_symbol");
        System.out.println(bb_symbol.size());
    }

    @org.junit.Test
    public void test2() {
        BBKLine bbkLine = new BBKLine();
        bbkLine.setAsset("BTC");
        bbkLine.setSymbol("BTC_USDT");
        bbkLine.setFrequence(1);
        bbkLine.setMinute(26388150L);
        bbkLine.setHigh(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setLow(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setOpen(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setClose(new BigDecimal(8727.580000000000000000000000000000 + ""));
        bbkLine.setVolume(new BigDecimal(0.019791000000000000000000000000 + ""));
        //向集合中插入元素，并设置分数
        templateDB0.opsForZSet().add(BbKLineKey.KLINE_BB_TASK_FROM_EXP + "BTC" + ":" + "BTC_USDT", JSON.toJSONString(bbkLine), 26388150);
        Set<String> range = templateDB0.opsForZSet().range("testkline:from_exp:repair:BB:" + "BTC" + ":" + "BTC_USDT:1", 0, -1);
        System.out.println(range);
    }
@Autowired
BBKlineBuild bbKlineBuild;

    @org.junit.Test
    public void testoubsub(){
        bbKlineBuild.trigger();
    }

    @org.junit.Test
    public void test(){
        final long l = TimeUnit.MILLISECONDS.toMinutes(1583394926831L);
        System.out.println("l = " + l);
    }

    @org.junit.Test
    public void test3(){
        String asset="BTC";
        String symbol="BTC_USDT";
        Set<ZSetOperations.TypedTuple<String>> task = templateDB0.opsForZSet().rangeWithScores(BbKLineKey.KLINE_BB_TASK_FROM_EXP + asset + ":" + symbol, 0, -1);
        if (CollectionUtils.isEmpty(task)) {
            System.out.println("task = " + task);
        }
        for (ZSetOperations.TypedTuple<String> tuple : task) {
            final BBKLine bbkLine = JSON.parseObject(tuple.getValue(), BBKLine.class);
            System.out.println("k线1分钟时间为："+bbkLine.getMinute());
        }
    }

    @org.junit.Test
    public void test4(){
        BigDecimal newkLine=new BigDecimal("8450.22");
        BigDecimal oldLine=new BigDecimal("8410.22");

        System.out.println(newkLine.min(oldLine));
    }
    @org.junit.Test
    public void test5(){
        BigDecimal newkLine=BigDecimal.ZERO;
        BigDecimal oldLine=new BigDecimal("8410.22");
        newkLine=oldLine;



        System.out.println(newkLine);
    }

    @org.junit.Test
    public void test6(){
         Long min = 26395388L;
        Set<String> range = klineTemplateDB2.opsForZSet().rangeByScore("kline:BB:USDT:BTC_USDT:1", min, min+5);
        if (!range.isEmpty()) {
             ArrayList<String> list = new ArrayList<>(range);
            for (String s : list) {
                BBKLine bbkLine = JSON.parseObject(s,BBKLine.class);
                System.out.println("bbkLine = " + bbkLine);
            }
        }
    }

}
