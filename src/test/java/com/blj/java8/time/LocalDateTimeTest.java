package com.blj.java8.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * LocalDateTime与timestamp、Date的转换
 * @author BaiLiJun  on 2020/1/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalDateTimeTest {

    //LocalDateTime转Date
    @Test
    public void test1() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.from(localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant());
        System.out.println("date = " + date);
    }

    //Date转LocalDateTime
    @Test
    public void test2() {
        Date date = new Date();
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);
    }

    //LocalDateTime转时间戳
    @Test
    public void test3() {
        LocalDateTime localDateTime = LocalDateTime.now();
        long timestamp = localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println("timestamp = " + timestamp);
    }


    //时间戳转LocalDateTime
    @Test
    public void test4() {
        long timestamp = 1578457517157L;
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println("localDateTime = " + localDateTime);
    }


    //LocalDateTime加减时间
    @Test
    public void test5() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime plusDays = localDateTime.plusDays(1L);
        System.out.println("加一天后的时间为： " + plusDays);

        LocalDateTime minusDays = localDateTime.minusDays(1L);
        System.out.println("减一天时间 " + minusDays);
        long l = minusDays.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println("减一天时间的时间戳为："+l);

    }

}
