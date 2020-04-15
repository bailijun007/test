package com.blj.bigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@ActiveProfiles("local")
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

        BigDecimal price = orderAmount.divide(volume,10, RoundingMode.DOWN);
        System.out.println("price = " + price);

    }

    @Test
    public void test4(){
        BigDecimal decimal = new BigDecimal("0.000000000000000");
//        if(decimal.equals(BigDecimal.ZERO)){
//            System.out.println("true");
//        }else {
//            System.out.println("false");
//        }

        if(decimal.compareTo(BigDecimal.ZERO)==0){
            System.out.println("true");
        }else {
            System.out.println("false");
        }

    }


    @Test
    public void test5(){
        BigDecimal c2cLockedVolume =BigDecimal.ZERO;
        c2cLockedVolume =new BigDecimal("12");
        System.out.println("c2cLockedVolume = " + c2cLockedVolume);
    }

}
