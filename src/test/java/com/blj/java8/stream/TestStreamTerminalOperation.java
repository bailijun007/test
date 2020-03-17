package com.blj.java8.stream;

import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * stream接口终端操作
 *
 * anyMatch表示，判断的条件里，任意一个元素成功，返回true
 *
 * allMatch表示，判断条件里的元素，所有的都是，返回true
 *
 * noneMatch跟allMatch相反，判断条件里的元素，所有的都不是，返回true
 *
 * @author BaiLiJun  on 2020/3/17
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestStreamTerminalOperation {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        long count = strs.stream().filter(str -> str.equals("a")).count();
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(count);// 4
    }


    @Test
    public void test2(){
         List<User> userList = userMapper.selectAll();
        if (userList.stream().map(User::getAge).anyMatch(age->age>60)) {
            System.out.println("存在60岁以上的用户 " );
            System.out.println("60岁以上的用户有："+userList.stream().map(User::getAge).filter(age -> age > 60).count()+"个");
        }
    }

}
