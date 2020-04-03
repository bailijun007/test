package com.blj.redis.pubsub;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 生产者
 * @author BaiLiJun  on 2020/3/17
 */
@Service
public class Pub {
    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;


    public void sendMessage(String channel, String message) {
        templateDB0.convertAndSend(channel, message);
    }
    public void sendMessage(String channel, User user) {
        templateDB0.convertAndSend(channel, JSON.toJSONString(user));
    }

    public void batchSendMessages(String channel, List<User> user) {
        templateDB0.convertAndSend(channel, JSON.toJSONString(user));
    }

}
