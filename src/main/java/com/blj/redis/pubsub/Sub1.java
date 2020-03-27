package com.blj.redis.pubsub;

import com.alibaba.fastjson.JSONObject;
import com.blj.pojo.User;
import com.blj.redis.pubsub.constant.BbKLineKey;
import com.blj.redis.pubsub.constant.PubSubRedisKey;
import com.blj.redis.pubsub.kline.BBKlineBuild;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 消费者
 * @author BaiLiJun  on 2020/3/17
 */
@Slf4j
@Component("sub1")
public class Sub1 {


    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @PostConstruct
    public void trigger(){
        String channel = PubSubRedisKey.TEST_PUBSUB;

        templateDB0.getConnectionFactory().getConnection().subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                String msg = new String(message.getBody());
                log.info("收到推送消息:{}" , msg);
//                 User user = JSONObject.parseObject(msg, User.class);

            }
        },channel.getBytes());
    }
}
