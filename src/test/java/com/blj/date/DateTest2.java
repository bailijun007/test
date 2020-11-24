package com.blj.date;

import com.blj.util.DateUtil;
import com.blj.util.DateUtil2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author BaiLiJun on 2020/11/23 5:25 下午
 */
public class DateTest2 {
    public static void main(String[] args) {
        Long times = 1386665666777L;

        Date date = new Date(times);

        System.out.println(date);


        Date preDate = DateUtil2.addDays(new Date(), -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(preDate);
        System.out.println("format = " + format);

    }
}
