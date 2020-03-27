package com.blj.redis.pubsub.test;

import com.blj.pojo.User;
import com.blj.redis.pubsub.Pub;
import com.blj.redis.pubsub.constant.PubSubRedisKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author BaiLiJun  on 2020/3/27
 */
@Slf4j
@Component
public class TestPubDemo {
    @Autowired
    private Pub pub;


    @Scheduled(cron = "*/1 * * * * *")
    public void testPub() {
        User u = new User(10000L, "张三", 20, "123456");
        pub.sendMessage(PubSubRedisKey.TEST_PUBSUB, u);
        log.info("发送消息：[{}]",u.toString());
    }

}
