package com.blj.date;

import com.blj.util.DateUtil;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaiLiJun  on 2019/12/25
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class DateTest {

    @Test
    public void test1(){
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
    public void testDateTime(){
        long timestamp = System.currentTimeMillis();
        DateTime dateTime = new DateTime(timestamp);
        System.out.println("dateTime="+dateTime);
        DateTime minus8HoursTime = dateTime.minusHours(8);
        System.out.println("minus8HoursTime="+minus8HoursTime);
        System.out.println("减去8小时，转成时间戳为："+minus8HoursTime.getMillis());
    }

}
