package com.blj.java8.stream;

import com.blj.pojo.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun on 2020/12/14 12:01 下午
 */
public class Test {
    public static void main(String[] args) {

        List<User> list = Arrays.asList(
                new User( "zhangsan", 20,"123456",true),
                new User("lisi", 25, "123456",true),
                new User("zhaoliu", 25, "123456",true),
                new User( "wangerma", 34, "123456",true),
                new User( "zhangergou", 28, "12345",false)
        );

        List<User> userList = list.stream().filter(u -> u.getDeleteFlag() != true).collect(Collectors.toList());

        System.out.println("userList = " + userList);


    }
}
