package com.blj.redis.pubsub.test;

import com.blj.pojo.User;
import com.blj.redis.pubsub.Pub;
import com.blj.redis.pubsub.constant.PubSubRedisKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author BaiLiJun  on 2020/3/27
 */
@Slf4j
@Component
public class TestPubDemo {
    @Autowired
    private Pub pub;

    @Value("${test.pubsub.enable}")
    private Integer pubsubEnable;

    //    @Scheduled(cron = "*/1 * * * * *")
    public void testPub1() {
        if (pubsubEnable == 0) {
            return;
        }
        User u = new User(UUID.randomUUID().toString().substring(0, 9), new Random().nextInt(50), "123456");
        pub.sendMessage(PubSubRedisKey.TEST_PUBSUB, u);
        log.info("发送消息：[{}]", u);
    }


    @Scheduled(cron = "*/1 * * * * *")
    public void testPub2() {
        if (pubsubEnable == 0) {
            return;
        }
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User u = new User(UUID.randomUUID().toString().substring(0, 9), new Random().nextInt(i + 20), "123456");
            userList.add(u);
        }
        pub.batchSendMessages(PubSubRedisKey.TEST_PUBSUB, userList);
        log.info("发送批量消息：[{}]", userList);
    }

}
