package com.blj.bigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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

}
