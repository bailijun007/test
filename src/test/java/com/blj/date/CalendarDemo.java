package com.blj.date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * @author BaiLiJun  on 2020/3/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CalendarDemo {

    /**
     * 毫秒时间戳转成分钟时间戳
     */
    @Test
    public void toMinute(){
        long currentMinute = TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().getTimeInMillis());
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

}
