package com.blj.redis.pubsub;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blj.mapper.bootTest1.UserMapper;
import com.blj.pojo.User;
import com.blj.redis.pubsub.constant.BbKLineKey;
import com.blj.redis.pubsub.constant.PubSubRedisKey;
import com.blj.redis.pubsub.kline.BBKlineBuild;
import com.blj.redis.pubsub.vo.BbTradeVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 消费者
 *
 * @author BaiLiJun  on 2020/3/17
 */
@Slf4j
@Component("sub1")
public class Sub1 {

    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            1,
            1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(10000000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Autowired
    private UserMapper userMapper;

    //    @PostConstruct
//    @Scheduled(cron = "*/5 * * * * *")
    public void trigger1() {
        String channel = PubSubRedisKey.TEST_PUBSUB;

        final ArrayBlockingQueue<User> queue = new ArrayBlockingQueue<>(10000000);

        templateDB0.getConnectionFactory().getConnection().subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                String msg = new String(message.getBody());
                log.info("收到推送消息:{}", msg);
                User user = JSONObject.parseObject(msg, User.class);
                log.info("JSONObject.parseObject:{}", user);
                queue.add(user);
            }
        }, channel.getBytes());

        threadPool.execute(() -> {
            while (true) {
                User user = queue.poll();
                if (null != user) {
//                    userMapper.insert(user);
                    log.info("保存成功:{}", user);
                }
            }
        });
    }

//    @Scheduled(cron = "*/5 * * * * *")
    public void trigger2() {
        String channel = PubSubRedisKey.TEST_PUBSUB;

        final ArrayBlockingQueue<List<User>> queue = new ArrayBlockingQueue<>(10000000);

        templateDB0.getConnectionFactory().getConnection().subscribe(new MessageListener() {
            @Override
            public void onMessage(Message message, byte[] pattern) {
                String msg = new String(message.getBody());
                log.info("收到推送消息:{}", msg);
                List<User> users = JSONObject.parseArray(msg, User.class);
                queue.add(users);
            }
        }, channel.getBytes());

        threadPool.execute(() -> {
            while (true) {
                 List<User> userList = queue.poll();
                if (!CollectionUtils.isEmpty(userList)) {
                    final List<User> sortedList = userList.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
                    userMapper.batchSave(sortedList);
                    log.info("批量保存成功:{}", sortedList);
                }
            }
        });
    }

}
