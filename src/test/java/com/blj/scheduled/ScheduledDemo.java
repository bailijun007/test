package com.blj.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2020/3/3
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ScheduledDemo {

    @Test
    @Scheduled(cron = "*/5 * * * * *")
    public void test1() throws InterruptedException {
        log.info("test1, 5秒执行一次，每次执行sleep 8s");
        Thread.sleep(8000L);
    }

    @Test
    @Scheduled(cron = "*/5 * * * * *")
    public void test2() {
        log.info("test2, 5秒执行一次，不sleep");
    }




}
