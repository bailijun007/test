package com.blj.list;

import com.blj.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author BaiLiJun on 2020/7/9 11:18 上午
 */
public class ListTest2 {
    public static void main(String[] args) {
//        listDemo();
        List<User> list = Arrays.asList(
                new User(1L, "zhangsan", 29, "123456"),
                new User(2L, "lisi", 25, "123456"),
                new User(3L, "zhaoliu", 25, "123456"),
                new User(4L, "wangerma", 34, "123456"),
                new User(5L, "zhangergou", 28, "12345")
        );

        Stream<User> stream = list.stream();
        if (1==1) {
            stream = stream.filter(u->u.getName().equals("zhangsan"));
        }
        if (2==2) {
            stream = stream.filter(u->u.getPassword().equals("123456"));
        }
        if (3==3) {
            stream = stream.filter(u->u.getAge()>=28);
        }

        list = stream.collect(Collectors.toList());
        System.out.println("list = " + list);

    }

    private static void listDemo() {
        List<Object> list = Collections.emptyList();
        System.out.println("list = " + list);

        List<User> list2 = new ArrayList<>();
        if (1 == 1) {
            System.out.println("list2 = " + list2);
        }
    }
}
