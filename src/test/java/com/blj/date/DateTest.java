package com.blj.date;

import com.blj.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaiLiJun  on 2019/12/25
 */
@SpringBootTest
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

}
