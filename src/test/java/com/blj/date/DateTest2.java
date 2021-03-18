package com.blj.date;

import com.blj.util.DateUtil;
import com.blj.util.DateUtil2;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author BaiLiJun on 2020/11/23 5:25 下午
 */

public class DateTest2 {
    public static void main(String[] args) {

     //   timestampToDate();

        // 集合对象排序
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String s = changeDateOfDay(2);
        String s2 = changeDateOfDay(-2);
        List<TestVo> list = Arrays.asList(new TestVo(date, date, dateFormat.format(date)),
                new TestVo(date, date, s),
                new TestVo(date, date, s2));

        System.out.println("list = " + list);


        list.sort(Comparator.nullsLast(Comparator.comparing(m -> dateFormat.format(DateUtil2.strToDate(m.getApplyDateTime())))));


        System.out.println("list = " + list);
    }


    // 时间戳转Date
    public static void timestampToDate() {
        Long times = 1386665666777L;

        Date date = new Date(times);

        System.out.println(date);
    }

    // 测试改变日期（加一天，减一天）
    public static String changeDateOfDay(int n) {
        Date preDate = DateUtil2.addDays(new Date(), n);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(preDate);
        System.out.println("format = " + format);
        return format;
    }


}
