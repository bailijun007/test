package com.blj.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

/**
 * @author BaiLiJun  on 2020/1/9
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RandomTest {

    @Test
    public void test1(){
        long l = new Random().nextLong();
        System.out.println("l = " + l);
    }





}
