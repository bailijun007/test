package com.blj.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String remoteHostName;
    @Value("${spring.redis.port}")
    private int remotePort;
    @Value("${spring.redis.password}")
    private String remotePassword;

    @Primary
    @Bean("cf0")
    public RedisConnectionFactory redisConnectionFactory0() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(remoteHostName);
        conf.setPort(remotePort);
        RedisPassword password = RedisPassword.of(remotePassword);
        conf.setPassword(password);
        LettuceConnectionFactory cf = new LettuceConnectionFactory(conf);
        cf.setDatabase(0);
        return cf;
    }

    @Bean("cf5")
    public RedisConnectionFactory redisConnectionFactory5() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(remoteHostName);
        RedisPassword password = RedisPassword.of(remotePassword);
        conf.setPassword(password);
        conf.setPort(remotePort);

        LettuceConnectionFactory cf = new LettuceConnectionFactory(conf);
        cf.setDatabase(5);
        return cf;
    }

    @Bean(name = "templateDB0")
    public StringRedisTemplate template0(@Qualifier("cf0") RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean(name = "templateDB5")
    public StringRedisTemplate template5(@Qualifier("cf5") RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
