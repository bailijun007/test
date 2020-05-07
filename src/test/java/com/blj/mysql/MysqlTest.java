package com.blj.mysql;

import com.blj.mapper.bootTest1.UserMapper;
import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun on 2020/1/5 0005
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MysqlTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test1(){
        Instant start = Instant.now();


        List<User> list = userMapper.findAll();

        List<User> userList = list.stream().skip(10 * 0)
                .limit(10)
                .collect(Collectors.toList());

        for (User user : userList) {
            System.out.println("user = " + user);
        }

        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println("duration.toMillis() = " + duration.toMillis());

    }


    @Test
    public void test2(){
        Instant start = Instant.now();

        Map<String,Object> map=new HashMap<>();
        List<User> userList = userMapper.queryList(map);


        for (User user : userList) {
            System.out.println("user = " + user);
        }

        Instant end = Instant.now();

        Duration duration = Duration.between(start, end);
        System.out.println("duration.toMillis() = " + duration.toMillis());

    }

}
