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

    @Test
    public void test1() {
        String s = "1583897760000,7921.41,7921.41,7921.41,7921.41,0.007";
        final String[] split = s.split(",");


    }
}
