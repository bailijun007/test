//package com.blj.redis.pubsub;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
//import org.springframework.data.redis.listener.RedisMessageListenerContainer;
//
///**
// * 监听器：使用这个的前提是配置文件要开启事件监听：notify-keyspace-events Ex
// * @author BaiLiJun  on 2020/3/5
// */
//public class CheckKeyExpire extends KeyspaceEventMessageListener {
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//
//    public CheckKeyExpire(RedisMessageListenerContainer listenerContainer) {
//        super(listenerContainer);
//    }
//
//    @Override
//    protected void doHandleMessage(Message message) {
//        String channel = new String(message.getChannel());
//        if (channel.equals("__keyevent@0__:expired")) {
//
//            String body = new String(message.getBody());
//            System.out.println("消息id为：" + body + "成功处理");
//            redisTemplate.delete("fail_" + new String(message.getChannel())+"_"+ body);
//            //如果这里有状态改变为接受成功的消息之类的，可以在这里操作数据库
//        }
//    }
//
//}
