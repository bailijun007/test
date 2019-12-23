package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.PosLevelVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test3 {

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Resource(name = "templateDB5")
    private StringRedisTemplate templateDB5;


    /**
     * RedisTemplate中zset类型的使用
     */
    @Test
    public void test1() {
        //返回集合内元素的排名，以及分数（从小到大）
        Set<ZSetOperations.TypedTuple<String>> tuples = templateDB5.opsForZSet().rangeWithScores("markPrice:pc:history:BTC:ETH_USD", 0, -1);
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

}
