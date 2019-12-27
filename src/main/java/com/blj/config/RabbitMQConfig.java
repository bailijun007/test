package com.blj.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author BaiLiJun  on 2019/12/27
 */
@Configuration
public class RabbitMQConfig {

    //库存交换机
    public static final String STORY_EXCHANGE = "STORY_EXCHANGE";
    //订单交换机
    public static final String ORDER_EXCHANGE = "ORDER_EXCHANGE";

    //库存队列
    public static final String STORY_QUEUE = "STORY_QUEUE";
    //订单队列
    public static final String ORDER_QUEUE = "ORDER_QUEUE";

    //库存路由键
    public static final String STORY_ROUTING_KEY = "STORY_ROUTING_KEY";
    //订单路由键
    public static final String ORDER_ROUTING_KEY = "ORDER_ROUTING_KEY";


    //创建库存交换机
    @Bean
    public DirectExchange getStoryExchange() {
        return new DirectExchange(STORY_EXCHANGE);
    }
    //创建库存队列
    @Bean
    public Queue getStoryQueue() {
        return new Queue(STORY_QUEUE);
    }
    //库存交换机和库存队列绑定
    @Bean
    public Binding bindStory() {
        return BindingBuilder.bind(getStoryQueue()).to(getStoryExchange()).with(STORY_ROUTING_KEY);
    }


    //创建订单交换机
    @Bean
    public DirectExchange getOrderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    //创建订单队列
    @Bean
    public Queue getOrderQueue() {
        return new Queue(ORDER_QUEUE);
    }

    //订单交换机和订单队列绑定
    @Bean
    public Binding bindOrder() {
        return BindingBuilder.bind(getOrderQueue()).to(getOrderExchange()).with(ORDER_ROUTING_KEY);
    }


}
