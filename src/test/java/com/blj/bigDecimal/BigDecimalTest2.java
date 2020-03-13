package com.blj.bigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BigDecimalTest2 {

    /**
     * 最小值
     */
    @Test
    public void test1() {
        BigDecimal bigDecimal = new BigDecimal("456.15");
        BigDecimal bigDecima2 = new BigDecimal("4215.15");
        final BigDecimal min = bigDecimal.min(bigDecima2);
        System.out.println("min = " + min);

    }

    /**
     * 最大值
     */
    @Test
    public void test2() {
        BigDecimal bigDecimal = new BigDecimal("456.15");
        BigDecimal bigDecima2 = new BigDecimal("4215.15");
        final BigDecimal max = bigDecimal.max(bigDecima2);
        System.out.println("max = " + max);

    }

    @Test
    public void test3() {
        BigDecimal bigDecimal = new BigDecimal("10.00");
        BigDecimal bigDecima2 = new BigDecimal("3.00");

        BigDecimal[] decimals = bigDecimal.divideAndRemainder(bigDecima2);
        System.out.println(decimals[0]);
        System.out.println(decimals[1]);
    }

    /**
     * BigDecimal 转成double
     */
    @Test
    public void test4() {
        BigDecimal bigDecimal = new BigDecimal("148651534.15645");
        final double v = bigDecimal.doubleValue();
        System.out.println("v = " + v);
    }


    /**
     * BigDecimal 去掉末尾多余的0
     */
    @Test
    public void test5() {
        BigDecimal bigDecimal = new BigDecimal("148651534.156450000");
        System.out.println("bigDecimal = " + bigDecimal);

        System.out.println("去掉末尾的0，bigDecimal ="+bigDecimal.stripTrailingZeros());

    }


}
