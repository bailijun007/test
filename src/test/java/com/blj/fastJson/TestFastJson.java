package com.blj.fastJson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.blj.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2020/3/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
public class TestFastJson {

    @Test
    public void test1() {
        String s = "[1483200240000,956.54,956.54,956.54,956.54,0.48375944]";
        JSONArray ja = JSON.parseArray(s);
        System.out.println("分钟时间戳：" + ja.getLong(0));
        System.out.println("开盘价：" + ja.getBigDecimal(1));
        System.out.println("最高价：" + ja.getBigDecimal(2));
        System.out.println("最低价：" + ja.getBigDecimal(3));
        System.out.println("收盘价：" + ja.getBigDecimal(4));
        System.out.println("总数：" + ja.getBigDecimal(5));
    }

    @Test
    public void test2() {
        String s = "[0,1483200240000,956.54,956.54,956.54,956.54,0.48375944]";
        JSONArray ja = JSON.parseArray(s);
        System.out.println("是否需要：" + ja.getInteger(0));
        System.out.println("分钟时间戳：" + ja.getLong(1));
        System.out.println("开盘价：" + ja.getBigDecimal(2));
        System.out.println("最高价：" + ja.getBigDecimal(3));
        System.out.println("最低价：" + ja.getBigDecimal(4));
        System.out.println("收盘价：" + ja.getBigDecimal(5));
        System.out.println("总数：" + ja.getBigDecimal(6));
    }

    /**
     * 进来使用fastjson的ToJsonString方法代替java的toString方法
     */
    @Test
    public void testToJsonString() {
        User user = new User();
        user.setId(0L);
        user.setName("testToJsonString");
        user.setAge(0);
        user.setPassword("123456");
        user.setCreateDate(LocalDate.now());
        user.setCreateTime(LocalDateTime.now());
        String jsonString = JSON.toJSONString(user);
        log.info("jsonString={}", jsonString);
    }

    /**
     * ParseObject
     */
    @Test
    public void testParseObject() {
        User user = new User();
        user.setId(0L);
        user.setName("testToJsonString");
        user.setAge(0);
        user.setPassword("123456");
        user.setCreateDate(LocalDate.now());
        user.setCreateTime(LocalDateTime.now());
        String jsonString = JSON.toJSONString(user);
        User user1 = JSON.parseObject(jsonString, User.class);
        log.info("JSON.parseObject={}", user1);
    }

    /**
     * ParseArray
     */
    @Test
    public void testParseArray() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(Long.parseLong(i+""));
            user.setName("testParseArray"+i);
            user.setAge(i);
            user.setPassword("123456");
            user.setCreateDate(LocalDate.now());
            user.setCreateTime(LocalDateTime.now());
            list.add(user);
        }

        String jsonString = JSON.toJSONString(list);
        List<User> userList = JSON.parseArray(jsonString, User.class);
        log.info("JSON.parseArray={}", userList);
    }

}
