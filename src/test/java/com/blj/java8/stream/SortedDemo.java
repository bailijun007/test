package com.blj.java8.stream;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java8 stream多字段排序
 * https://www.cnblogs.com/kuanglongblogs/p/11230250.html
 * @author BaiLiJun  on 2020/3/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SortedDemo {
    List<User> list = Arrays.asList(
            new User(1L, "zhangsan", 29, "123456"),
            new User(2L, "lisi", 29, "123456"),
            new User(3L, "zhaoliu", 25, "123456"),
            new User(4L, "wangerma", 34, "123456"),
            new User(5L, "zhangergou", 28, "123456")
    );

    //返回 对象集合以类属性一升序 属性二升序
    @Test
    public void test() {
         List<User> userList = list.stream().sorted(Comparator.comparing(User::getAge).thenComparing(User::getId)).collect(Collectors.toList());
        for (User user : userList) {
            System.out.println("user = " + user);
        }
    }
    //返回 对象集合以类属性一降序 属性二升序 注意两种写法
    @Test
    public void test2() {
        ///先以属性一升序,升序结果进行属性一降序,再进行属性二升序
//        List<User> userList = list.stream().sorted(Comparator.comparing(User::getAge).reversed().thenComparing(User::getId)).collect(Collectors.toList());

        //先以属性一降序,再进行属性二升序
        List<User> userList = list.stream().sorted(Comparator.comparing(User::getAge,Comparator.reverseOrder()).thenComparing(User::getId)).collect(Collectors.toList());

        for (User user : userList) {
            System.out.println("user = " + user);
        }
    }

}
