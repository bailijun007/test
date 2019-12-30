package com.blj.java8.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParsePosition;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * @author BaiLiJun  on 2019/12/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DateFormatterTest {

    @Test
    public void test1() {
        Instant now = Instant.now();
        System.out.println("当前时间戳：" + now.toEpochMilli());

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("当前时间：" + dateTime);

        //下个周三是几号
        LocalDateTime time = dateTime.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        System.out.println("下个周三是几号 = " + time);

        //DateTimeFormatter 格式化时间/日期
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String format = dtf.format(dateTime);
        System.out.println("格式化时间/日期（默认）= " + format);


        //DateTimeFormatter 自定义格式化时间/日期
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String format1 = dtf2.format(dateTime);
        String format1 = dateTime.format(dtf2);
        System.out.println("自定义格式化时间:" + format1);

        //Date转换为LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
        System.out.println("date转换为LocalDateTime = " + localDateTime);
        String format2 = localDateTime.format(dtf2);
        System.out.println("自定义格式化时间: " + format2);

        //LocalDateTime转换为Date
//        Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());//当前时间戳
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());//当前时间
        System.out.println("LocalDateTime转换为Date = " + date);

        //String类型的时间转成LocalDateTime
        String str="2019-12-29 12:12:12";
        LocalDateTime ldt = LocalDateTime.parse(str, dtf2);
        System.out.println("String类型的时间转成LocalDateTime = " + ldt);
        String format3 = ldt.format(dtf2);
        System.out.println("自定义格式化时间: " + format3);


        //LocalDateTime转成String类型的时间
        String format4 = dtf2.format(dateTime);
        System.out.println("LocalDateTime转成String类型的时间 = " + format4);

    }


    //注意：在后期使用Period.between（）方法来获取，相差天数、相差月数的时候，
    // 发现只能计算同月的天数、同年的月数，不能计算隔月的天数以及隔年的月数！！！
    //正确计算2个日期之间的天数为：
    @Test
    public void test2(){
        long now = LocalDate.now().toEpochDay();
        LocalDate old = LocalDate.of(2018, 6, 25);
        long oldDate = old.toEpochDay();
        System.out.println(now - oldDate);
    }



}
