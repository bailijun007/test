package com.blj.java8.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.*;
import java.time.format.DateTimeFormatter;
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
        long timestamp = now.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
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
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println("localDate = " + localDate);
    }


    @Test
    public void testLocalDate5() {
        long timestamp = System.currentTimeMillis();
         LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        LocalDateTime oldTime=LocalDateTime.of(2020,1,10,23,23,12,12);
        System.out.println("20分钟后时间为"+oldTime.plusMinutes(20));
        if (oldTime.plusMinutes(20).isBefore(localDateTime)) {
            //更新：待支付为超时已取消
            System.out.println("更新：待支付为超时已取消");
        }else {
            System.out.println("---------");
        }
    }

    @Test
    public void test(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyyMM");
         LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);
        final String s = now.toString();
        System.out.println("s = " + s);
        final String[] split = s.split("T");
        System.out.println(split[0]);

         LocalDate localDate = now.toLocalDate();
        final String format = localDate.format(formatter);
        System.out.println("format = " + format);



    }

}
