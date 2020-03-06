//package com.blj.config;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//
///**
// * 因为不同的消费者不能公用同一个client所以把获取连接的bean设为多例的。
// *
// * 这里一开始做的时候遇到好多坑，后来发现template构造函数有个factory了，然后试着注入到配置类里面，果然可以，解决了一开始的bug
// * @author BaiLiJun  on 2020/3/5
// */
//@Configuration
//public class PubsubRedisConfig {
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;
//
//    @Bean
//    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
//    public RedisConnection redisConnection() {
//        return redisConnectionFactory.getConnection();
//    }
//
//    @Bean
//    public RedisMessageListenerContainer container() {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(redisConnectionFactory);
//        return container;
//    }
//
//    @Bean
//    public PubSubTable pubSubTable() {
//        return new PubSubTable();
//    }
//
//    @Bean
//    public CheckKeyExpire checkKeyExpire(
//    ) {
//        return new CheckKeyExpire(container());
//    }
//
//
//}
