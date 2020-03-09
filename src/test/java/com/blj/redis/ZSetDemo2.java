package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.redis.pubsub.vo.BBKLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author BaiLiJun  on 2020/3/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ZSetDemo2 {
    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

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
                .rangeByScore("kline:from_exp:repair:BB:" + "BTC" + ":" + "BTC_USDT:1", minDouble, minDouble+5);

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
                BBKLine bbkLine1 = JSON.parseObject(s,BBKLine.class);
                list.add(bbkLine1);
            }
            }
        for (BBKLine line : list) {
            System.out.println("line = " + line);
        }

        }


    }


