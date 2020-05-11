package com.blj.java8.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * LocalDate、LocalTime、 LocalDateTime 类的实
 * 例是不可变的对象，分别表示使用
 * IS0-8601日历系统的日期、时间、日期和时间。
 * 它们提供了简单的日期或时间，并不包含当前的时间信息。
 * 也不包含与时区相关的信息。
 *
 * @author BaiLiJun on 2019/12/29 0029
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class LocalDateTest {

    //时区 ZonedDate. ZonedTime、ZonedDateTime
    @Test
    public void test8(){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Cayenne"));
        System.out.println("now = " + now);

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime dateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println("dateTime = " + dateTime);
    }


    @Test
    public void test7(){
        ZoneId.getAvailableZoneIds().forEach(System.out::println);
    }

    //5.DateTimeFormatter :格式化时间/日期
    @Test
    public void test6(){
        DateTimeFormatter formatter1=DateTimeFormatter.ISO_DATE;
        LocalDate ldt = LocalDate.now();
        String format = ldt.format(formatter1);
        System.out.println("format = " + format);

        System.out.println("--------------------" );

        DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String format1 = formatter2.format(ldt);
        System.out.println("format1 = " + format1);

        //字符串解析回时间格式
        LocalDate parse = ldt.parse(format1, formatter2);
        System.out.println("parse = " + parse);

    }


    //4.TemporalAdjuster :时间校正器
    @Test
    public void test5(){
        LocalDate date = LocalDate.now();
        System.out.println("date = " + date);

        LocalDate updateDay = date.withDayOfMonth(10);
        System.out.println("updateDay = " + updateDay);

        //下个月的第一天是几号
        LocalDate nextDayOfMonth = date.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("nextDayOfMonth = " + nextDayOfMonth);

        //自定义，比如：下个工作日
        LocalDate date1 = date.with(l -> {
            LocalDate localDate = (LocalDate) l;
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return localDate.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return localDate.plusDays(2);
            } else {
                return localDate.plusDays(1);
            }
        });

        System.out.println("下个工作日 = " + date1);

    }



    /*
     *3. Duration :计算两个“时间"之间的间隔
     *   Period: 计算两个“日期”之间的问隔
     *
     */

    @Test
    public void test4() {
        LocalDate ld1 = LocalDate.of(2018, 6, 25);
        LocalDate ld2 = LocalDate.now();
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }

    @Test
    public void test3() {
        Instant start = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();


        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());

        System.out.println("-----------------------");

        LocalTime start2 = LocalTime.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime end2 = LocalTime.now();
        System.out.println(Duration.between(start2, end2).toMillis());


    }


    //2. Instant :时间戳(以Unix元年: 1970年1月1日00: 00:00到某个时间之间的毫秒值)
    @Test
    public void test2() {
        Instant instant = Instant.now();//默认获取 UTC 时区
        System.out.println("instant = " + instant);

        OffsetDateTime localTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println("localTime = " + localTime);

        //当前时间转毫秒（时间戳）
        System.out.println(instant.toEpochMilli());

        //时间戳转时间
        String s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(instant.toEpochMilli()));
        System.out.println("时间戳转时间 " + s);


        //操作时间戳
        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println("instant1 = " + instant1);//instant1 = 1970-01-01T00:01:00Z


    }


    //1. LocalDate LocalTime LocalDateTime
    @Test
    public void test1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now = " + now);

        LocalDateTime dateTime = LocalDateTime.of(2019, 12, 29, 22, 15, 12);
        System.out.println("指定日期:" + dateTime);


        //操作日期，比如加2年
        LocalDateTime plusYears = now.plusYears(2L);
        System.out.println("2年后时间为： " + plusYears);

        //减2个月
        LocalDateTime minusMonths = now.minusMonths(2L);
        System.out.println("2个月前时间为 " + minusMonths);

        //指定日期减一天
        System.out.println("指定日期减一天的时间为：" + dateTime.minusDays(1L));

        System.out.println(now.getYear());
        System.out.println(now.getMonth().getValue());
        System.out.println(now.getDayOfMonth());
        System.out.println(now.getHour());
        System.out.println(now.getMinute());
        System.out.println(now.getSecond());
        System.out.println("今天是周几：" + now.getDayOfWeek());


    }



}
