package com.blj.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author BaiLiJun  on 2020/3/4
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class CalendarDemo {

    /**
     *
     * 毫秒时间戳转成分钟时间戳
     */
    @Test
    public void toMinute(){
       long l=1584728760000L;
        System.out.println("l = " + l);
        long currentMinute = TimeUnit.MILLISECONDS.toMinutes(l);
        System.out.println("currentMinute = " + currentMinute); //currentMinute = 26395439
    }

    /**
     * 分钟时间戳转成毫秒时间戳
     */
    @Test
    public void test2(){
      Long minute=  26395388L;
        final long l = TimeUnit.MINUTES.toMillis(minute);
        System.out.println("l = " + l);//l = 1583723280000  =>2020/3/9 11:8:0

        final long l2 = TimeUnit.MINUTES.toMillis(minute+5);
        System.out.println("l2 = " + l2);//l2 = 1583723580000  => 2020/3/9 11:13:0
    }




    @Test
    public void addMinute(){
        long ms=1584728760000L;
        long maxMs = TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(ms) + 1) - 1;
        System.out.println("maxMs = " + maxMs);
    }




    @Test
    public void TestIsEffectiveDate() throws ParseException {
        String format = "yyyy-MM-dd";
        // Date nowTime = new SimpleDateFormat(format).parse("2020-08-01 09:27:00");
        Date startTime = new SimpleDateFormat(format).parse("2020-08-01 09:27:00");
        Date endTime = new SimpleDateFormat(format).parse("2020-10-31 09:27:59");
        System.out.println(isEffectiveDate(new Date(), startTime, endTime));

    }

    /**
     * @description: 判断一个时间点是否在一个时间段内，在返回true，不在返回false
     * @param: nowTime 当前时间
     * @param: startTime 开始时间
     * @param: endTime 结束时间
     * @return: boolean
     * @author: baiLiJun
     * @date: 2020/10/28
     */
    public  boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
