package com.blj.string;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2020/4/10
 */
@ActiveProfiles("local")
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStr {
    @Test
    public void test1(){
        String str ="tBTCUSD";
        //endsWith 以什么结尾
        if (str.endsWith("USD")){
             String concat = str.concat("T");
             //concat 拼接字符串
            System.out.println("concat = " + concat);
        }

        //contains 包含某个字符串
        if(str.contains("USD")){
            //substring 截取字符串
            final String substring = str.substring(1, str.length());
            System.out.println("substring = " + substring);
        }

        final String concat = str.substring(1, str.length()).concat("T");
        System.out.println("concat = " + concat);

    }


    @Test
    public void test2(){
        String str ="tBTCUSD";
        //equalsIgnoreCase 忽略大小写比较
        final boolean tbtcusd = str.equalsIgnoreCase("tbtcusd");
        System.out.println("tbtcusd = " + tbtcusd);
    }
}
