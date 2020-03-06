//package com.blj.redis.pubsub;
//
//import com.alibaba.fastjson.JSON;
//import com.blj.pojo.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.connection.Message;
//import org.springframework.data.redis.connection.MessageListener;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.lang.Nullable;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author BaiLiJun  on 2020/3/5
// */
//@Component
//public class Comsumer1 implements MessageListener {
//
//
//    @Autowired
//    private PubSubTable pubSubTable;
//    @Autowired
//    private StringRedisTemplate redisTemplate;
//
//    public void addChannel(String topic) {
//
//        pubSubTable.addComsumer(topic, this.getClass().getSimpleName());
//        System.out.printf("%s 订阅一个主题%s%n", this.getClass().getSimpleName(), topic);
//
//    }
//
//
//    @Override
//    public void onMessage(Message message, @Nullable byte[] pattern) {
//        String name = this.getClass().getSimpleName();
//        String topic = new String(message.getChannel());
//        String content = new String(message.getBody());
//        User messageVO = JSON.parseObject(content, User.class);
//        //如果这个取出来的不是正确的id，丢弃并记录。
//        String b = redisTemplate.opsForList().rightPop(topic + "_" + name);
//        if (b != null && b.equals(topic + "_" + messageVO.getName())) {
//            User userVO = JSON.parseObject(messageVO.getName(), User.class);
//            System.out.printf("%s从主题%s收到消息:%s%n", name, topic, content);//业务处理
//            System.out.printf("消息内容:%s%n", userVO.toString());
//            redisTemplate.expire(topic + "_" + name + "_" + messageVO.getMessageId(), 1, TimeUnit.NANOSECONDS);
//        } else {
//            //把他设为fail,准备重新处理
//            redisTemplate.opsForValue().set("fail_" + topic + "_" + name + "_" + messageVO.getMessageId(), content);
//        }
//    }
//
//}
