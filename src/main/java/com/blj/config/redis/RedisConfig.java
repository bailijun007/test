package com.blj.config.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;

    @Primary
    @Bean("cf0")
    public RedisConnectionFactory redisConnectionFactory0() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(hostName);
        conf.setPort(port);
        RedisPassword password = RedisPassword.of(this.password);
        conf.setPassword(password);
        LettuceConnectionFactory cf = new LettuceConnectionFactory(conf);
        cf.setDatabase(0);
        return cf;
    }

    @Bean("cf5")
    public RedisConnectionFactory redisConnectionFactory5() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(hostName);
        RedisPassword password = RedisPassword.of(this.password);
        conf.setPassword(password);
        conf.setPort(port);

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
