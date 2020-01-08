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
 * LocalDate 与timestamp、Date的转换
 * @author BaiLiJun  on 2020/1/8
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class LocalDateTest2 {
    //LocalDate转时间戳
    @Test
    public void testLocalDate1() {
        LocalDate now = LocalDate.now();
        long timestamp = now.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
        System.out.println("timestamp = " + timestamp);
    }

    //LocalDate转Date
    @Test
    public void testLocalDate2() {
        LocalDate nowLocalDate = LocalDate.now();
        Date date = Date.from(nowLocalDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        System.out.println("date = " + date);
    }

    //Date转LocalDate
    @Test
    public void testLocalDate3() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        System.out.println("localDate = " + localDate);
    }

    //时间戳转LocalDate
    @Test
    public void testLocalDate4() {
        long timestamp = System.currentTimeMillis();
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        System.out.println("localDate = " + localDate);
    }

}
