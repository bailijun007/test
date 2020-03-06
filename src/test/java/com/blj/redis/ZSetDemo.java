package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.redis.pubsub.vo.BBKLine;
import com.blj.pojo.User;
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
 * RedisTemplate中zset类型的使用
 *
 * @author BaiLiJun  on 2019/12/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ZSetDemo {

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Resource(name = "templateDB5")
    private StringRedisTemplate templateDB5;


    public static final String key = "ranking-list";

    /**
     * RedisTemplate中zset类型的使用
     */
    @Test
    public void test1() {
        //返回集合内元素的排名，以及分数（从小到大）
        Set<ZSetOperations.TypedTuple<String>> tuples = templateDB5.opsForZSet().rangeWithScores("markPrice:pc:history:BTC:BTC_USD", 0, -1);
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            System.out.println(tuple.getValue() + " : " + tuple.getScore());
        }
    }

    @Test
    public void test2() {
        // 第一步：排序   reverseRange：按照分数由大到小排序（指定位置区间）
        Set<String> strings = templateDB5.boundZSetOps("markPrice:pc:history:BTC:ETH_USD").reverseRange(0, 0);
//        System.out.println("strings = " + strings);
        ArrayList<String> list = new ArrayList<>(strings);
//        System.out.println(list);
        String s = list.get(0);
        String[] split = s.split("#");
        System.out.println(split[0]);
        System.out.println(split[1]);

    }


    @Test
    public void test3() {
        // 第一步：排序   reverseRange：按照分数由大到小排序（指定位置区间）
        Set<String> strings = templateDB5.boundZSetOps("markPrice:pc:history:BTC:BTC_USD").reverseRange(0, 0);
//        System.out.println("strings = " + strings);
        ArrayList<String> list = new ArrayList<>(strings);
//        System.out.println(list);
        String s = list.get(0);
        String[] split = s.split("#");
        System.out.println(split[0]);
        System.out.println(split[1]);

    }


    @Test
    public void putElement() {
        User user1 = new User(100L, "zhangsan", 22, "123456");
        User user2 = new User(200L, "lisi", 23, "123456");
        //向集合中插入元素，并设置分数
        templateDB0.opsForZSet().add("userList", JSON.toJSONString(user1), 2.1);
        templateDB0.opsForZSet().add("userList", JSON.toJSONString(user2), 3.1);
        //按照排名先后(从小到大)打印指定区间内的元素, -1为打印全部
        Set<String> range = templateDB0.opsForZSet().range("userList", 0, -1);
        System.out.println(range);
    }

    @Test
    public void test4() {
        //向集合中插入元素，并设置分数
        templateDB0.opsForZSet().add(key, "p1", 2.1);

        //向集合中插入多个元素
        DefaultTypedTuple<String> tuple1 = new DefaultTypedTuple<String>("p2", 1.1);
        DefaultTypedTuple<String> tuple2 = new DefaultTypedTuple<String>("p3", 3.1);
        templateDB0.opsForZSet().add(key, new HashSet<>(Arrays.asList(tuple1, tuple2)));

        //按照排名先后(从小到大)打印指定区间内的元素, -1为打印全部
        printZSet();
    }

    @Test
    public void test5() {
        printZSet();

        //从集合中删除指定元素
        templateDB0.opsForZSet().remove("ranking-list", "p1");
        printZSet();

    }

    @Test
    public void test6() {
        //为指定元素加分
        Double score = templateDB0.opsForZSet().incrementScore("ranking-list", "p1", 2);
        System.out.println(score);//返回加分后的得分
        printZSet();
    }

    @Test
    public void test7() {
        //返回指定成员的排名（从小到大）
        Long rank = templateDB0.opsForZSet().rank(key, "p1");
        //从大到小
        Long reverseRank = templateDB0.opsForZSet().reverseRank(key, "p1");
        System.out.println(rank);
        System.out.println(reverseRank);
    }

    @Test
    public void test8() {
        //返回集合内元素的排名，以及分数（从小到大）
        Set<ZSetOperations.TypedTuple<String>> tuples = templateDB0.opsForZSet().rangeWithScores(key, 0, -1);
        for (ZSetOperations.TypedTuple<String> tuple : tuples) {
            System.out.println(tuple.getValue() + " : " + tuple.getScore());
        }
    }

    @Test
    public void test9() {
        //返回集合内元素在指定分数范围内的排名（从小到大）
        Set<String> ranking = templateDB0.opsForZSet().rangeByScore(key, 0, 5);
        System.out.println(ranking);
        //带偏移量和个数，下例意为从第二个开始，要三个
        Set<String> ranking2 = templateDB0.opsForZSet().rangeByScore(key, 0, 5, 1, 3);
        System.out.println(ranking2);
    }

    @Test
    public void test10() {
        //返回集合内指定分数范围的成员个数
        Long count = templateDB0.opsForZSet().count("ranking-list", 0, 2);
        System.out.println(count);
        //返回集合内的成员个数
        Long size = templateDB0.opsForZSet().size("ranking-list");//等同于zCard(key);
        System.out.println(size);
    }

    @Test
    public void test11() {
        //获得指定元素的分数
        Double score = templateDB0.opsForZSet().score("ranking-list", "p1");
        System.out.println(score);
    }

    @Test
    public void test12() {
        printZSet();
        //删除指定索引范围的元素
        templateDB0.opsForZSet().removeRange("ranking-list", 0, 0);
        printZSet();
    }

    @Test
    public void test13() {
        //删除指定分数范围内的元素
        printZSet();
        templateDB0.opsForZSet().removeRangeByScore("ranking-list", 4, 5);
        printZSet();
        templateDB0.opsForZSet();
    }

    private void printZSet() {
        //按照排名先后(从小到大)打印指定区间内的元素, -1为打印全部
        Set<String> range = templateDB0.opsForZSet().range(key, 0, -1);
        System.out.println(range);

//reverseRange 从大到小
//        Set<String> reverseRange = templateDB0.opsForZSet().reverseRange(key, 0, -1);
//        System.out.println(reverseRange);
    }


    @Test
    public void saveKline() {
        long minute = 26388150L;
        BBKLine bbkLine = new BBKLine();
        bbkLine.setAsset("BTC");
        bbkLine.setSymbol("BTC_USDT");
        bbkLine.setFrequence(1);
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
                .rangeByScore("kline:from_exp:repair:BB:" + "BTC" + ":" + "BTC_USDT:1", minDouble, minDouble);

        if (!range.isEmpty()) {
            final String s = new ArrayList<>(range).get(0);
            System.out.println("s = " + s);
//            JSON字符串转JSON对象
            BBKLine bbkLine1 = JSON.parseObject(s,BBKLine.class);
            System.out.println("bbkLine1 = " + bbkLine1);
            System.out.println(bbkLine1.getAsset());
        }


    }

    @Test
    public void notifyUpdate() {
        //向集合中插入元素，并设置分数
        templateDB0.opsForZSet().add("kline:from_exp:update:BB:BTC:BTC_USDT:26388150", "BTC#BTC_USDT#26388150", 26388150);

    }

}
