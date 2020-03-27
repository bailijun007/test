package com.blj;

import com.blj.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.PostConstruct;

/**
 * 主启动类
 *
 * @author BaiLiJun  on 2019/12/20
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.blj.mapper")
public class Application implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Autowired
    private RedisService redisService;
    /**
     * redis初始化商品的库存量和信息
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        redisService.put("watch", "10", 20);
    }

    @Value("${spring.profiles.active:}")
    private String profile;

    @PostConstruct
    private Object printEnv() {
        logger.warn("===========profile:{}============", profile);
        return null;
    }

}
