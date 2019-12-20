package com.blj.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * redis 数据库切换
 * @author BaiLiJun  on 2019/12/20
 */
@Component
public class RedisUtil {
//    @Autowired
    private StringRedisTemplate stringRedisTemplate;


//    @Resource(name = "template0")
//    private StringRedisTemplate template0;
//
//
//    @Resource(name = "template5")
//    private StringRedisTemplate template5;

    public void setRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    /**
     * 切换数据库
     * @param num
     */
    public  void setDataBase(int num) {
        LettuceConnectionFactory connectionFactory = (LettuceConnectionFactory) stringRedisTemplate.getConnectionFactory();
        if (connectionFactory != null && num != connectionFactory.getDatabase()) {
            connectionFactory.setDatabase(num);
            this.stringRedisTemplate.setConnectionFactory(connectionFactory);
            connectionFactory.resetConnection();
        }
    }


    public StringRedisTemplate getRedisTemplate() {
        return this.stringRedisTemplate;
    }

}
