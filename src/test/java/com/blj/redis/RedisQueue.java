package com.blj.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author BaiLiJun  on 2020/3/3
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisQueue {

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Resource(name = "templateDB5")
    private StringRedisTemplate templateDB5;

    //生产者的主方法
    @Test
    public void test1() {
//        templateDB0
    }



}
