package com.blj.java8.forkJoin;

import com.blj.pojo.ForkJoinCal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author BaiLiJun on 2019/12/29 0029
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ForkJoinTest {


    @Test
    public void test1() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinCal task = new ForkJoinCal(0L, 50000000000L);
        Long invoke = pool.invoke(task);
        System.out.println("invoke = " + invoke);

        Instant end = Instant.now();

        pool.shutdown();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

    //普通for循环
    @Test
    public void test2() {
        Instant start = Instant.now();

        long sum = 0;
        for (long i = 0; i < 50000000000L; i++) {
            sum += i;

        }
        System.out.println("sum = " + sum);
        Instant end = Instant.now();

        //耗费时间为：23362
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());

    }

    // java 8 并行流
    @Test
    public void test3() {
        Instant start = Instant.now();

        long sum = LongStream.rangeClosed(0, 50000000000L)
                .parallel()
                .reduce(0, Long::sum);
        System.out.println("sum = " + sum);

        Instant end = Instant.now();


        //耗费时间为：19657
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());

    }

}
