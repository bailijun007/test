package com.blj.java8.optional;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author BaiLiJun on 2020/7/23 11:14 上午
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestOptional2 {

    List<User> list = Arrays.asList(
            new User(1L, "zhangsan", 29, "123456"),
            new User(2L, "lisi", 25, "11111"),
            new User(3L, "zhaoliu", 25, "123456"),
            new User(4L, "wangerma", 34, "22222"),
            new User(5L, "zhangergou", 28, "123456"),
            new User(5L, "zhangergou", 28, "123456")
    );

    // stream + optional
    @Test
    public void test(){
        if(!CollectionUtils.isEmpty(list)){
            User user = list.stream().filter(u -> u.getAge() > 26).findFirst().orElse(null);
            System.out.println("user = " + user);
        }
    }


    @Test
    public void test2(){
        if(!CollectionUtils.isEmpty(list)){
            String s = list.stream().filter(u -> u.getAge() >46).findFirst().map(User::getName).map(u -> u.toUpperCase()).orElse(null);
            System.out.println("s = " + s); //s =  null
        }
    }


    @Test
    public void test3(){
        User user = new User(1L, "zhangsan", 29, "123456");
        Optional<User> user1 = Optional.ofNullable(user);
        LocalDate localDate = user1.map(User::getCreateDate).orElse(LocalDate.now());
        // 有则直接用，没有则给默认值
         user.setCreateDate(localDate);
        System.out.println("user = " + user);

    }

}
