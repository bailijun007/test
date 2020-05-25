package com.blj.localDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BaiLiJun  on 2020/5/9
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class LocalDateDemo {

    /**
     * 使用java 8的Period的对象计算两个LocalDate对象的时间差，
     * 严格按照年、月、日计算，如：2020-05-09 与 2018-03-12 相差 2 年 1 个月 27 天
     */
    @Test
    public void test() {
        LocalDate today = LocalDate.now();
        System.out.println("Today：" + today);
        int year = 2018;
        Month month = Month.MARCH;
        int dayOfMonth = 12;
        LocalDate oldDate = LocalDate.of(year, month, dayOfMonth);
        System.out.println("OldDate：" + oldDate);

        Period p = Period.between(oldDate, today);
        System.out.printf("目标日期距离今天的时间差：%d 年 %d 个月 %d 天\n", p.getYears(), p.getMonths(), p.getDays());
    }

    /**
     * 现将2个String转成LocalDate 类型，
     * 然后计算2个时间差
     * 最后打印并格式化时间（按年月）
     */
    @Test
    public void test2() {
        String str = "2018-03-12";
        String str2 = "2020-05-09";
        String[] split = str.split("-");
        String[] split2 = str2.split("-");

        String s = split[0] + split[1];
        String s2 = split2[0] + split2[1];

        int p1 = Integer.parseInt(s);
        int p2 = Integer.parseInt(s2);

        List<Integer> list=new ArrayList<>();
            for (int i = p1; i <= p2; i++) {
                 Boolean b = isData(i + "");
                 if(b){
                     list.add(i);
                 }
            }

        System.out.println(list.size());

    }

    /**
     * 另外在使用SimpleDateFormat对String类型的日期进行parse的时候，如果传入的日期为：2017-08-60，
     * 这种错误的日期，Java默认会按照日期的信息对其进行转换，formatter.parse("2017-08-60");，
     * 得到的日期为2017-09-29，而如果不想进行这种转换，而直接将其判定为输入错误，
     * 则可以设置formatter.setLenient(false);，这时就会抛出java.text.ParseException异常了
     *
     * @param s
     * @return
     */
    public Boolean isData(String s) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        formatter.setLenient(false);
        try {
            Date date = formatter.parse(s);  //抛出转换异常
            final String format = formatter.format(date);
            System.out.println("format = " + format);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
