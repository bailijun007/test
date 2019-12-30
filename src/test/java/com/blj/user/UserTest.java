package com.blj.user;

import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author BaiLiJun  on 2019/12/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setCreateDate(LocalDate.now())
                .setCreateTime(LocalDateTime.now())
                .setName("李四")
                .setAge(20)
                .setPassword("123456");


        userMapper.insertSelective(user);
    }

    @Test
    public void query() {
        List<User> list = userMapper.selectAll();
        for (User user : list) {
            System.out.println("user = " + user);
        }


    }


}
