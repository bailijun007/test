package com.blj.pojo;

import java.util.concurrent.RecursiveTask;

/**
 * @author BaiLiJun on 2019/12/29 0029
 */
public class ForkJoinCal extends RecursiveTask<Long> {

    private Long satrt;

    private Long end;

    //临界值
    private static final Long THRESHOLD = 10000L;


    public ForkJoinCal(Long satrt, Long end) {
        this.satrt = satrt;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long l = end - satrt;
        long sum = 0;
        if (l <= THRESHOLD) {
            for (long i = satrt; i <= end; i++) {
                sum += i;
            }

            return sum;
        } else {
            long middle = (satrt + end) / 2;
            ForkJoinCal left = new ForkJoinCal(satrt, middle);
            ForkJoinCal right = new ForkJoinCal(middle + 1, end);
            left.fork();
            right.fork();

            return left.join()+right.join();
        }

    }
}
