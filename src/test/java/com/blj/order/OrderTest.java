package com.blj.order;

import com.blj.mapper.bootTest1.OrderMapper;
import com.blj.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/1/8
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class OrderTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void test1() {
        Instant now = Instant.now();
        System.out.println(now.toEpochMilli());
    }

    @Test
    public void test2() {
        LocalDate now = LocalDate.now();
        long timestamp = now.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        System.out.println("LocalDate转时间戳 = " + timestamp);//1578412800000
    }

    //时间戳转LocalDateTime(LocalDate)
    @Test
    public void test4() {
        long timestamp =1578412800000L;
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);

        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        System.out.println("localDate = " + localDate);

    }


    @Test
    public void test3() {
        Map<String, Object> map=new HashMap<>();
        map.put("createdBegin","1578412800000");
        map.put("createdEnd",Instant.now().toEpochMilli());
        List<Order> orders = orderMapper.queryList(map);
        for (Order order : orders) {
            System.out.println("order = " + order);
        }
    }

}
