package com.blj;

import com.blj.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 主启动类
 *
 * @author BaiLiJun  on 2019/12/20
 */
@SpringBootApplication
@MapperScan("com.blj.mapper")
public class Application implements ApplicationRunner {

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


}
