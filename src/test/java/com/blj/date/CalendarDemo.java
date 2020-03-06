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

    @Test
    public void test1(){
        long currentMinute = TimeUnit.MILLISECONDS.toMinutes(Calendar.getInstance().getTimeInMillis());
        System.out.println("currentMinute = " + currentMinute);
    }
}
