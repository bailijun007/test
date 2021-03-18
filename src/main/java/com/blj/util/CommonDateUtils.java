package com.blj.util;

import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * TODO 有bug 有时间优化修改
 * @author BaiLiJun  on 2020/5/9
 */
public final class CommonDateUtils {


    /**
     * 计算2个字符串之间的月份差
     *
     * @param str
     * @param str2
     * @return 所有月份
     */
    public static List<Integer> getTimeDifference(String str, String str2) {
        String[] split = str.split("-");
        String[] split2 = str2.split("-");

        String s = split[0] + split[1];
        String s2 = split2[0] + split2[1];

        int p1 = Integer.parseInt(s);
        int p2 = Integer.parseInt(s2);

        List<Integer> list = new ArrayList<>();
        for (int i = p1; i <= p2; i++) {
            Boolean b = isData("yyyyMM", i + "");
            if (b) {
                list.add(i);
            }
        }

        return list;
    }


    /**
     * 字符串转时间戳
     *
     * @param str
     * @return
     */
    public static Long stringToTimestamp(String str) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startLdt = LocalDate.parse(str, dtf);
        Long begin = startLdt.atStartOfDay(TimeZone.getTimeZone("UTC").toZoneId()).toInstant().toEpochMilli();
        return begin;
    }

    /**
     * LocalDate 转时间戳
     *
     * @param localDate
     * @return
     */
    public static Long localDateToTimestamp(LocalDate localDate) {
        return localDate.atStartOfDay(TimeZone.getTimeZone("UTC").toZoneId()).toInstant().toEpochMilli();
    }


    /**
     * 时间戳转String
     *
     * @param timestamp
     * @return
     */
    public static String timestampToString(Long timestamp) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate beginDate = Instant.ofEpochMilli(timestamp).atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDate();
        return dtf.format(beginDate);
    }

    /**
     * 判断是否是日期
     *
     * @param pattern 指定的格式
     * @param s
     * @return
     */
    public static Boolean isData(String pattern, String s) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        formatter.setLenient(false);
        try {
            Date date = formatter.parse(s);  //抛出转换异常
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String getDefaultDateTime(String startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime dateTime = LocalDateTime.now();
        //如果开始时间，结束时间没有值则给默认今天时间
        if (StringUtils.isEmpty(startTime)) {
            startTime = formatter.format(dateTime);
        }
        return startTime;
    }


    public static String[] getStartAndEndTime(String startTime, String endTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (org.apache.commons.lang3.StringUtils.isEmpty(startTime)) {
            startTime = getDefaultDateTime(startTime);
            LocalDate startDate = stringToLocalDate(startTime);
            startTime = startDate.format(pattern);
            LocalDate endDate = startDate.plusDays(1);
            endTime = endDate.format(pattern);
        }
        String[] startAndEndTime = {startTime, endTime};
        return startAndEndTime;
    }

    //LocalDateTime  --> String
    public static String localDateTimeToString() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String format = localDateTime.format(formatter);
        return format;
    }


    // String -->LocalDate
    public static LocalDate stringToLocalDate(CharSequence text) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(text, dateTimeFormatter);
        return localDate;
    }

    // String -->LocalDate or LocalDateTime
    public static LocalDate stringToLocalDate(String pattern, CharSequence text) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.parse(text, dateTimeFormatter);
        return localDate;
    }

    public static Long[] getStartAndEndTimeByLong(Long startTime, Long endTime) {
        if (null == startTime) {
            LocalDate localDate = LocalDate.now();
            startTime = localDate.atStartOfDay(TimeZone.getTimeZone("UTC").toZoneId()).toInstant().toEpochMilli();
            LocalDate plusDays = localDate.plusDays(1);
            endTime = plusDays.atStartOfDay(TimeZone.getTimeZone("UTC").toZoneId()).toInstant().toEpochMilli();
        }
        Long[] startAndEndTime = {startTime, endTime};
        return startAndEndTime;
    }

    // 返回的UTC时间戳
    public static Long getUTCTime() throws Exception {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = TimeZone.getTimeZone("UTC");
        cal.setTimeZone(tz);
        Long timeInMillis = cal.getTimeInMillis();
        return timeInMillis;
    }

    /**
     * 时间戳转UTC LocalDate
     *
     * @param timestamp
     * @return
     */
    public static LocalDate timestampToLocalDate(Long timestamp) {
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDate();
        return localDate;
    }

    /**
     * 时间戳转UTC LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDateTime();
        return localDateTime;
    }

    public static void main(String[] args) throws Exception {
        final Long utcTimeStr = getUTCTime();
        System.out.println("utcTimeStr = " + utcTimeStr);//1591173214973 --> 2020-06-03 16:33:34
        LocalDate localDate = LocalDate.now(TimeZone.getTimeZone("UTC").toZoneId());
//        final long utc = localDate.atStartOfDay(TimeZone.getTimeZone("UTC").toZoneId()).toInstant().toEpochMilli();
//        System.out.println("utc = " + utc);//1591142400000 --> 2020-06-03 08:00:00

        LocalDate toLocalDate = Instant.ofEpochMilli(utcTimeStr).atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDate();
        final LocalDate minusDays = toLocalDate.minusDays(2L);
        System.out.println("minusDays = " + minusDays);
//        final Long aLong = localDateToTimestamp(minusDays);
//        System.out.println("localDateToTimestamp = " + aLong);

        final Instant instant = LocalDateTime.now(TimeZone.getTimeZone("UTC").toZoneId()).toInstant(ZoneOffset.UTC);
        final long l = instant.toEpochMilli();
        System.out.println(" l = " + l);
        //一天等于多少毫秒：24*3600*1000
        long minusDay = (24 * 60 * 60 * 1000) * 2;
        final long l1 = l - minusDay;
        System.out.println("l1 = " + l1);
    }


}
