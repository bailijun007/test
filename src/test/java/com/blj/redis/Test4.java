package com.blj.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author BaiLiJun  on 2019/12/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test4 {
    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Test
    public void test1(){
        Long number = templateDB0.opsForValue().increment("number", -1L);
        System.out.println("number = " + number);
    }

}
