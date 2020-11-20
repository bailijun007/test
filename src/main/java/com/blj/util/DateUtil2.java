package com.blj.util;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: huanglin
 * @Date: 2018/9/5 下午3:07
 * @Version 2.0
 */
public class DateUtil2 {


    /**
     * 格式化日期的格式
     */
    public static final String FORMAT_DATETIME =  "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE =  "yyyy-MM-dd";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy.MM.dd";
    public static final String YYYYMM = "yyyyMM";

    /**
     * 一天的毫秒数
     */
    private static final int DAY_TIME = 1000 * 3600 * 24;

    /**
     * 月中的号数
     */
    private static final int MID_MONTH = 15;

    /**
     * 一年按照365天算
     */
    private static final int YEAR_DAY = 365;

    /**
     * 格式化时间yyyy-MM-dd HH:mm:ss
     * @param date 输入日期
     * @return yyyy-MM-dd HH:mm:ss 2018-03-12 23:22:22
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        return formatter.format(date);
    }

    public static String dateToStrYYYYMMDD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        return formatter.format(date);
    }

    public static String dateToStr(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    public static Date strToDateTime(String strDateTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDateTime, pos);
    }
    /**
     * 获取这个月的号数。
     * @param date 输入书剑
     * @return 这个月的第几号
     */
    public static int getMonthDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return  calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 判断是否是月中，是否需要增加。
     * @See MID_MONTH
     * @param date
     * @return 判断是否是月中
     */
    public static boolean isIdes(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return  calendar.get(Calendar.DAY_OF_MONTH) <= MID_MONTH;
    }

    /**
     * 这个月是否算一个月。
     * @param date
     * @return 15号前 0 ，15 号后1
     */
    public static int idesAddOne(Date date){
        return isIdes(date) ? 1 : 0;
    }

    /**
     * 给出入职时间或者，重制时间
     * @param start 年假计算开始时间
     * @param end  年假结束时间
     * @param maxAnnualLeave 这一年最大的年假时间
     * @return 这一年的年假
     */
    public static double thisAnnualLeave(Date start,Date end,int maxAnnualLeave){
        return differentDaysByMillisecond(start,end) / YEAR_DAY * maxAnnualLeave;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param start 判断开时间
     * @param end 结束时间
     * @return
     */
    public static int differentDaysByMillisecond(Date start,Date end) {
        return  (int)(end.getTime() - start.getTime()) / DAY_TIME;
    }

    /**
     * 两个日期是否同一年
     * @param left
     * @param right
     * @return
     */
    public static boolean isSameYear(Date left, Date right) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(left);
        calendar2.setTime(right);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * 两个日期是否同一月
     * @param left
     * @param right
     * @return
     */
    public static boolean isSameMonth(Date left, Date right) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(left);
        calendar2.setTime(right);

        boolean isSameYear = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
        boolean isSameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
        return isSameYear && isSameMonth;
    }

    /**
     * 两个日期是否为同一天
     * @param left
     * @param right
     * @return
     */
    public static boolean isSameDay(Date left, Date right) {

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(left);
        calendar2.setTime(right);

        boolean isSameYear = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
        boolean isSameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
        boolean isSameDay = calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
        return isSameYear && isSameMonth && isSameDay;
    }

    /**
     * 两个日期是否同月同日
     * @param left
     * @param right
     * @return
     */
    public static boolean isSameMonthAndDay(Date left, Date right) {

        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(left);
        calendar2.setTime(right);

        boolean isSameMonth = calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
        boolean isSameDay = calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
        return isSameMonth && isSameDay;
    }

    /**
     * 两个日期间相隔的天数
     * @param start
     * @param end
     * @return
     */
    public static Integer getBetweenDays(Date start, Date end) {
        LocalDate localDate1 = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return (int)(localDate2.toEpochDay() - localDate1.toEpochDay());
    }

    /**
     * 获取某年最后一天
     * @param date
     * @return
     */
    public static Date getYearLastDay(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(date);
        calendar2.clear();
        calendar2.set(Calendar.YEAR, calendar1.get(Calendar.YEAR));
        calendar2.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar2.getTime();
    }

    /**
     * 是否是今年
     * @param date
     * @return
     */
    public static boolean isThisYear(Date date){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date);
        calendar2.setTime(new Date());
        //当年为 0
        int workYear = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
        return workYear <= 0;
    }

    /**
     * 获取月份的月份
     * @param date
     * @return
     */
    public static int getMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取年份
     * @param date
     * @return
     */
    public static int getYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取某年第一天
     * @param date
     * @return
     */
    public static Date getYearFirstDay(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();

        calendar1.setTime(date);
        calendar2.clear();
        calendar2.set(Calendar.YEAR, calendar1.get(Calendar.YEAR));
        return calendar2.getTime();
    }

    /**
     * 获取两个日期 相差多少个月
     * @param left
     * @param right
     * @return
     */
    public static Integer getDiffMonthBetweenDate(Date left, Date right) {

        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(left);
        aft.setTime(right);
        int month = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        month += (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        if (aft.get(Calendar.DAY_OF_MONTH) < bef.get(Calendar.DAY_OF_MONTH)){
            month = month - 1;
        }

        return month;
    }

    /**
     * 获取两个日期 相差多少年
     * @param left
     * @param right
     * @return
     */
    public static Integer getDiffYearBetweenDate(Date left, Date right) {
        return getDiffMonthBetweenDate(left, right) / 12;
    }

    /**
     * 获取某月第一天
     * @param date
     * @return
     */
    public static Date getFirstDayInMonth(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date);

        calendar2.set(Calendar.YEAR, calendar1.get(Calendar.YEAR));
        calendar2.set(Calendar.MONTH, calendar1.get(Calendar.MONTH));
        calendar2.set(Calendar.DAY_OF_MONTH, 1);
        return calendar2.getTime();
    }

    public static int getAge(Date birthDay){
        Calendar cal = Calendar.getInstance();

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            }else{
                age--;
            }
        }

        return age;
    }

    /**
     * 判断两个时间区间是否有交集,精确到秒
     * @param startDate1
     * @param endDate1
     * @param startDate2
     * @param endDate2
     * @return
     */
    public static boolean hasCommonTime(Date startDate1,Date endDate1,Date startDate2,Date endDate2) {
        return startDate2.before(endDate1) && startDate1.before(endDate2);
    }

    /**
     * 把时间设置为当天0点
     * @param date
     */
    public static Date getStartTimeOfDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 把时间设置为当天23:59:59
     * @param date
     */
    public static Date getEndTimeOfDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

/*    public static void main(String[] args) {
        Date date = new Date(110,2,1,8,33,32);
        Date date1 = new Date(110,3,1,8,34,33);
        Date date2 = new Date(110,3,1,8,34,33);
        Date date3 = new Date(110,3,4,8,34,33);
        System.out.println();

    }*/

    /**
     * 获取某月的最后一天
     * @param date
     * @return
     */
    public static Date getLastDayInMonth(Date date) {
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date);

        calendar2.set(Calendar.YEAR, calendar1.get(Calendar.YEAR));
        calendar2.set(Calendar.MONTH, calendar1.get(Calendar.MONTH));
        calendar2.set(Calendar.DAY_OF_MONTH, 1);
        calendar2.set(Calendar.DATE, calendar2.getActualMaximum(Calendar.DATE));
        return calendar2.getTime();
    }

    /**
     * 获取某月的最后一天
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLastDayInMonth(LocalDateTime localDateTime) {
        YearMonth yearMonth = YearMonth.from(localDateTime);
        return LocalDateTime.from(yearMonth.atEndOfMonth().atStartOfDay());
    }

    /**
     * 获取某日加一年的时间
     * @param date
     * @return
     */
    public static Date addOneYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        return calendar.getTime();
    }

    public static Date addMonth(Date date,int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime();
    }

    public static Date addDays(Date date,int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        return calendar.getTime();
    }

    public static Date localDateToDate(LocalDate localDate){
        if(localDate == null){
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        if(localDateTime == null){
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取下一年的第一天
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getFirstDayNextYear(LocalDateTime localDateTime){
        int year = localDateTime.getYear() + 1;
        return LocalDateTime.of(year, Month.JANUARY, 1, 0,0,0);
    }

    /**
     * localDate自定义格式化
     * @param localDate
     * @return
     */
    public static String localDateFormat(LocalDate localDate,DateTimeFormatter fmt){
        return localDate.format(fmt);
    }

    /**
     * 年月转为日期，输出当月一号(如果格式是:yyyy-MM-dd则以当前日期为准)
     * @param yearMonth     格式:yyyy-MM
     * @param yearMonth     兼容格式:yyyy-MM-dd
     * @return
     */
    public static LocalDate strToLocalDate(String yearMonth){
        String[] strings = yearMonth.split("-");
        int year = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        if(strings.length == 3){
            int day = Integer.parseInt(strings[2]);
            return LocalDate.of(year, month, day);
        }
        return LocalDate.of(year, month, 1);
    }

    /**
     *
     * @param dateInString like "2018-08-16T15:23:01Z";
     * @return
     */
    public static LocalDateTime str2LocalDate(String dateInString){
        Instant instant = Instant.parse(dateInString);

        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        return result;
    }

    public static LocalDate str2LocalDate(String dateStr, String format){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateStr, df);
    }


    /**
     * 日期转为年月字符串
     * @param localDate
     * @return      格式：yyyy-MM
     */
    public static String localDateToStr(LocalDate localDate){
        if (localDate.getMonth().getValue() > 9) {
            return localDate.getYear()+"-"+localDate.getMonth().getValue();
        } else {
            return localDate.getYear()+"-0"+localDate.getMonth().getValue();
        }
    }

    /**
     * 日期转为年月日字符串
     * @param localDate
     * @return      格式：yyyy-MM-dd
     */
    public static String localDateToString(LocalDate localDate){
        if (localDate == null){
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return df.format(localDate);
    }

    /**
     * LocalDateTime转为年月字符串
     * @param localDateTime
     * @return      格式：yyyy-MM-dd HH:mm:ss
     */
    public static String localDateTimeToStr(LocalDateTime localDateTime){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String localTime = df.format(localDateTime);
        return localTime;
    }

    /**
     * 获取几个月后的时间
     * @param date
     * @param num
     * @return
     */
    public static Date getMonthLater(Date date, int num){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, num);
        return instance.getTime();
    }

    /**
     * 获取几天后的时间
     * @param date
     * @param num
     * @return
     */
    public static Date getDaysLater(Date date, int num){
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, num);
        return instance.getTime();
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    public static boolean isValidDate(String str) {
        boolean success=true;
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE);
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            success=false;
        }
        return success;
    }

    /**
     * Long转*String
     * @param times
     * @return      格式：yyyy-MM-dd HH:mm:ss
     */
    public static String convertLongToString(Long times){

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        date.setTime(times);

        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        return formatter.format(date);
    }

    public static String dateToStrYYYYMM(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMM);
        return formatter.format(date);
    }

    public static String dateToStrYYYY_MM_DD(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
        return formatter.format(date);
    }

    public static LocalDateTime longToLocalDateTime(Long l) {

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        date.setTime(l);

        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        Date date = new Date();
        Date startTimeOfDate = getStartTimeOfDate(date);
        String format = formatter.format(startTimeOfDate);
        System.out.println("format = " + format);
        long time = startTimeOfDate.getTime();
        System.out.println("time = " + time);

    }

}
