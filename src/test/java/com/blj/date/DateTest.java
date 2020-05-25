package com.blj.date;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitor;
import com.blj.util.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author BaiLiJun  on 2019/12/25
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class DateTest {

    @Test
    public void test1() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String beginDate = DateUtil.findLatelyDate(-2);
        System.out.println("beginDate = " + beginDate);
        try {
            Date parse = dateFormat.parse(beginDate);
            System.out.println(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 需要 <artifactId>joda-time</artifactId>
     */
    @Test
    public void testDateTime() {
        long timestamp = System.currentTimeMillis();
        DateTime dateTime = new DateTime(timestamp);
        System.out.println("dateTime=" + dateTime);
        DateTime minus8HoursTime = dateTime.minusHours(8);
        System.out.println("minus8HoursTime=" + minus8HoursTime);
        System.out.println("减去8小时，转成时间戳为：" + minus8HoursTime.getMillis());
    }

    @Test
    public void test2() {
        String tableName = "kline_data_";
        String text = "2020-05-06 16:51";
        String[] split = text.split("-");
        tableName = tableName + split[0] + split[1];
        System.out.println("tableName = " + tableName);
    }


    @Test
    public void test3() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime date = LocalDateTime.now();
        String format = dateTimeFormatter.format(date);
        System.out.println(format);
    }

    /**
     * 字符串转LocalDate，再转成时间戳
     */
    @Test
    public void test4() {
        String startTime = "2018-02-12";
        String endTime = "2020-05-09";
        final Long begin = stringToTimestamp(startTime);
        System.out.println("begin=" + begin);
        final Long end = stringToTimestamp(endTime);
        System.out.println("end=" + end);
    }

    private   Long stringToTimestamp(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.time.LocalDate startLdt = java.time.LocalDate.parse(str, dtf);
        Long begin = startLdt.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
        return begin;
    }

    private Long[] stringToTimestamp(String startTime, String endTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        java.time.LocalDate startLdt = java.time.LocalDate.parse(startTime, dtf);
        java.time.LocalDate endLdt = LocalDate.parse(endTime, dtf);
        long begin = startLdt.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
        long end = endLdt.atStartOfDay(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
        Long[] longs = {begin, end};
        return longs;
    }

    /**
     * 时间戳转成LocalDate，再转成字符串
     * begin=1518364800000   2018-02-12
     * end=1588953600000    2020-05-09
     */
    @Test
    public void test5() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Long begin = 1518364800000L;
        Long end = 1588953600000L;
        LocalDate beginDate = Instant.ofEpochMilli(begin).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = Instant.ofEpochMilli(end).atZone(ZoneId.systemDefault()).toLocalDate();
        String beginStr = dtf.format(beginDate);
        System.out.println("beginStr = " + beginStr);
        String endStr = dtf.format(endDate);
        System.out.println("endStr = " + endStr);
    }

    /**
     * LocalDate 转时间戳
     */
    @Test
    public void test6() {
        LocalDate now = LocalDate.now();
        long timestamp = now.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        System.out.println("timestamp = " + timestamp);
    }

}
