package com.blj.java8.time;

import com.blj.config.redis.jedis.MetadataRedisBeans;
import com.blj.config.redis.jedis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author BaiLiJun on 2019/12/29 0029
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestSimpleDateFormat {

    @Autowired
    @Qualifier("metadataRedisUtil")
    private RedisUtil metadataRedisUtil;

    //多线程操作时间类不安全
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return dateFormat.parse("20191229");
            }
        };


        List<Future<Date>> list = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<Date> future : list) {
            System.out.println(future.get());
        }
        pool.shutdown();
    }


    @Test
    public void test2() throws Exception {

        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return DateFormatThreadLocal.convert("20191229");
            }
        };


        List<Future<Date>> list = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<Date> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }

    //jdk1.8 LocalDate
    @Test
    public void test3() throws Exception {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

        Callable<LocalDate> task = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("20191229", dtf);
            }
        };


        List<Future<LocalDate>> list = new ArrayList<>();
        ExecutorService pool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<LocalDate> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }



    @Test
    public void test4() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.now();
        final String format = localDate.format(dtf);
        System.out.println("format = " + format);
        metadataRedisUtil.set(format,format);
    }


}


class DateFormatThreadLocal {
    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };

    public static Date convert(String source) throws ParseException {
        return df.get().parse(source);
    }

}