package com.blj.spin;

import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import com.blj.redis.pubsub.vo.BbTradeVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自旋
 *
 * @author BaiLiJun  on 2020/4/29
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestSpin {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void test() {
        List<User> list = new ArrayList<>();
        String createDateBegin = "2020-04-29";
        String createDateEnd = "2020-04-30";
        final int endLimit = 500;
        List<User> voList = userMapper.queryByTimeInterval(null, createDateBegin, createDateEnd, endLimit);
        list.addAll(voList);
        if (voList.size() < endLimit) {
            System.out.println("voList.size()=" + voList.size());
            return;
        } else {
            do {
                if (!CollectionUtils.isEmpty(voList)) {
                   //取最后一条数据的主键id
                    User user = voList.get(voList.size() - 1);
                    Long id = user.getId();
                    voList = userMapper.queryByTimeInterval(id, createDateBegin, createDateEnd, endLimit);
                    list.addAll(voList);
                }
            } while (!(voList.size() < endLimit));
            System.out.println("list.size()=" + list.size());
        }

        return;
    }


}
