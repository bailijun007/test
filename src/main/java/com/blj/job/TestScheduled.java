package com.blj.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author BaiLiJun  on 2020/4/29
 */
@Component
public class TestScheduled {

    @Scheduled(cron = "0 * * * * ?")
    @PostConstruct
    public void test(){
        System.out.println("初始化执行一次,之后每分钟执行一次");
    }

}
