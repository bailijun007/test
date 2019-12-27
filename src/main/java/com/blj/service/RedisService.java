package com.blj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 主要用来实现对redis得key和value初始化以及对value得操作
 * @author BaiLiJun  on 2019/12/27
 */
@Service
public class RedisService {

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;
    /**
     * 设置String键值对
     * @param key
     * @param value
     * @param millis
     */
    public void put(String key, String value, long millis) {
        templateDB0.opsForValue().set(key, value, millis, TimeUnit.MINUTES);
    }
    public void putForHash(String objectKey, String hkey, String value) {
        templateDB0.opsForHash().put(objectKey, hkey, value);
    }
    public <T> T get(String key, Class<T> type) {
        return (T) templateDB0.boundValueOps(key).get();
    }
    public void remove(String key) {
        templateDB0.delete(key);
    }
    public boolean expire(String key, long millis) {
        return templateDB0.expire(key, millis, TimeUnit.MILLISECONDS);
    }
    public boolean persist(String key) {
        return templateDB0.hasKey(key);
    }
    public String getString(String key) {
        return (String) templateDB0.opsForValue().get(key);
    }
    public Integer getInteger(String key) {
        String s = templateDB0.opsForValue().get(key);
        return Integer.parseInt(s) ;
    }
    public Long getLong(String key) {
        String s = templateDB0.opsForValue().get(key);
        return Long.parseLong(s) ;
    }
    public Date getDate(String key) {
        String s = templateDB0.opsForValue().get(key);
        return new Date(s) ;
    }

    /**
     * 对指定key的键值减一
     * @param key
     * @return
     */
    public Long decrBy(String key) {
      return templateDB0.opsForValue().increment(key, -1L);
    }



}
