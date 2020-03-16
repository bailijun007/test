package com.blj.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2020/3/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFastJson {

    @Test
    public void test1(){
        String s="[1483200240000,956.54,956.54,956.54,956.54,0.48375944]";
        JSONArray ja = JSON.parseArray(s);
        System.out.println("分钟时间戳："+ja.getLong(0));
        System.out.println("开盘价："+ja.getBigDecimal(1));
        System.out.println("最高价："+ja.getBigDecimal(2));
        System.out.println("最低价："+ja.getBigDecimal(3));
        System.out.println("收盘价："+ja.getBigDecimal(4));
        System.out.println("总数："+ja.getBigDecimal(5));
    }

}
