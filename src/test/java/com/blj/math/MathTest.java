package com.blj.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.time.Instant;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MathTest {

    @Test
    public void test1() {
        int random = (int) (Math.random() * 100);
        System.out.println("random = " + random);
    }

    //从数组中随机取出一个数
    @Test
    public void test2() {
        Long[] arr = {1234567L, 1111111L, 2222222L};
        int index = (int) (Math.random() * arr.length);
        Long aLong = arr[index];
        System.out.println(aLong);
    }


    //生成并返回订单编号
    @Test
    public void getOrderNo() {
        String s = "";
        for (int i = 0; i < 4; i++) {
            int random = (int) (Math.random() * 10);
            s += random;
        }
        Instant instant = Instant.now();
        long timestamp = instant.toEpochMilli();
        String prefix = "c2c";
        String sn = prefix + timestamp + s;

        System.out.println("sn = " + sn);
    }

}
