package com.blj.user;

import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/30
 */
@SpringBootTest
@ActiveProfiles("local")
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


    /**
     * 批量保存
     */
    @Test
    public void batchSave(){
        List<User> userList=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setCreateDate(LocalDate.now())
                    .setCreateTime(LocalDateTime.now())
                    .setName("李四"+i)
                    .setAge(20+i)
                    .setPassword("123456"+i);
            userList.add(user);
        }
        userMapper.batchSave(userList);


    }

    /**
     * 批量更新
     * 注意：这种方法必须在配置连接数据库url后面带一个参数 &allowMultiQueries=true，
     * 表示允许批量操作，例 jdbc:mysql://localhost:3306/mysqlTest?characterEncoding=utf-8&allowMultiQueries=true
     */
    @Test
    public void batchUpdateWithTwoParam(){
        List<User> userList=new ArrayList<>();
       long id=1000L;
        for (int i = 1; i <=7; i++) {
            User user = new User();
            user.setAge(60+i);
            userList.add(user);
        }

        final int i = userMapper.batchUpdateWithTwoParam(userList, id);
        System.out.println("i = " + i);
    }


    @Test
    public void batchUpdate2(){
        List<User> userList=new ArrayList<>();
        for (int i = 1; i <=7; i++) {
            User user = new User();
            user.setAge(30+i);
            user.setId(Long.parseLong(i+""));

            userList.add(user);
        }

        userMapper.batchUpdate(userList);
    }

    @Test
    public void queryAll() {
        List<User> list = userMapper.selectAll();
        for (User user : list) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("age", "20");
        Integer count = userMapper.queryCount(map);
        System.out.println("count = " + count);
    }


}
