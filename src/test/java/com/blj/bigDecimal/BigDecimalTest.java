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
public class BigDecimalTest {

    @Test
    public void test1(){
        BigDecimal fabiAmt=new BigDecimal("5");
        String orderAmount = fabiAmt.stripTrailingZeros().toPlainString();
        System.out.println("orderAmount = " + orderAmount);

    }

    @Test
    public void test2(){
        BigDecimal fabiAmt=new BigDecimal("5");
        String orderAmount = fabiAmt.toString();
        System.out.println("orderAmount = " + orderAmount);

    }


    @Test
    public void test3(){
        BigDecimal orderAmount =new BigDecimal("700");
        BigDecimal volume =new BigDecimal("97.5");

        BigDecimal price = orderAmount.divide(volume,10, RoundingMode.HALF_DOWN);
        System.out.println("price = " + price);

    }

}
