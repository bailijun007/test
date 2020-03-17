package com.blj.redis.pubsub;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author BaiLiJun  on 2020/3/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestPubsub {

    @Autowired
    private Pub pub;

    @Test
    public void test() throws InterruptedException{
        User u  = new User(10000L, "张三", 20, "123456");

//        pub.sendMessage("test_pubsub", "我发消息了!!!");
        pub.sendMessage("test_pubsub", u);
        Thread.sleep(100);//jackson 反向序列化慢

    }

}
