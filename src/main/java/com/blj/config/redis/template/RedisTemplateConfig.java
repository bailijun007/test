package com.blj.config.redis.template;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author BaiLiJun  on 2020/3/6
 */
@Configuration
public class RedisTemplateConfig {

    @Value("${kline.redis.host}")
    private String klineRedisHostName;
    @Value("${kline.redis.port}")
    private int klineRedisPort;
    @Value("${kline.redis.password}")
    private String klineRedisPassword;

    @Bean("klineCf2")
    public RedisConnectionFactory redisConnectionFactory0() {
        LettuceConnectionFactory cf = new LettuceConnectionFactory(standaloneConfiguration(2));
        return cf;
    }



    private RedisStandaloneConfiguration standaloneConfiguration(int dataBase) {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(klineRedisHostName);
        conf.setPort(klineRedisPort);
        conf.setPassword(RedisPassword.of(klineRedisPassword));
        conf.setDatabase(dataBase);
        return conf;
    }

    @Bean(name = "klineTemplateDB2")
    public StringRedisTemplate tradeTemplate2(@Qualifier("klineCf2") RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

}
