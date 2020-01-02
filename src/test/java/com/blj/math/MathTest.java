package com.blj.math;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MathTest {

    @Test
    public void test1(){
        int random = (int)(Math.random()*100);
        System.out.println("random = " + random);
    }


}
