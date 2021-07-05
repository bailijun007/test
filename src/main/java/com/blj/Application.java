package com.blj;

import com.blj.pojo.InstA;
import com.blj.pojo.TestImportBeanDefinitionRegistrar;
import com.blj.pojo.TestImportSelector;
import com.blj.pojo.User;
import com.blj.redis.pubsub.test.TestPubDemo;
import com.blj.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 主启动类
 *
 * @author BaiLiJun  on 2019/12/20
 */
//@Import(value = {InstA.class})
//@Import(value = {TestImportBeanDefinitionRegistrar.class}
//@Import(value = {TestImportSelector.class})
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableScheduling
@MapperScan("com.blj.mapper")
public class Application implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            2,
            Runtime.getRuntime().availableProcessors() + 1,
            2L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(10000000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy()
    );

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
//        TestPubDemo bean = applicationContext.getBean(TestPubDemo.class);
//        threadPool.execute(()->bean.testPub());
    }

    @Autowired
    private RedisService redisService;

    /**
     * redis初始化商品的库存量和信息
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisService.put("watch", "10", 20);
    }

    @Value("${spring.profiles.active:local}")
    private String profile;

    @PostConstruct
    private Object printEnv() {
        logger.warn("===========profile:{}============", profile);
        return null;
    }

    //    @Lazy
//    @Scope("prototype")
    @Order(2)
    @Bean("zhangsan")
    public User user() {
        logger.info("user 初始化完成。。。");
        return new User("张三", 25, "123456");
    }

    //    @Lazy
//    @Scope("prototype")
//    @Order(1)
//    @Bean("lisi")
//    public User user2() {
//        logger.info("user2 初始化完成。。。");
//        return new User("李四", 25, "123456");
//    }

}
