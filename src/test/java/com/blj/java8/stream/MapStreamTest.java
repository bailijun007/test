package com.blj.java8.stream;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 使用stream将list转成map
 * @author BaiLiJun  on 2020/2/19
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class MapStreamTest {

    List<User> list = Arrays.asList(
            new User(1L, "zhangsan", 29, "123456"),
            new User(2L, "lisi", 25, "123456"),
            new User(3L, "zhaoliu", 25, "123456"),
            new User(4L, "wangerma", 34, "123456"),
            new User(5L, "zhangergou", 28, "123456")
    );

    @Test
    public void listToMapByObjectValue(){
        // value 为对象 student -> student jdk1.8返回当前对象
        Map<Long, User> map = list.stream().collect(Collectors.toMap(User::getId, user -> user));
        // 遍历打印结果
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }



    @Test
    public void listToMapByNameValue(){
        // value 为对象中的属性
        Map<Long, String> map = list.stream().collect(Collectors.toMap(User::getId, User::getName));
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }


}
