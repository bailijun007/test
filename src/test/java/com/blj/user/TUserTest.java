package com.blj.user;

import com.blj.entity.TUser;
import com.blj.mapper.TUserDao;
import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import com.blj.util.CommonIntegerUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
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
public class TUserTest {
    @Autowired
    private TUserDao userMapper;


//    @Autowired
//    private SecondOrderMapper secondOrderMapper;

    @Test
    public void test() {
        List<TUser> tUsers = userMapper.queryAllByLimit(0, 10);
        System.out.println("tUsers = " + tUsers);
    }

}
