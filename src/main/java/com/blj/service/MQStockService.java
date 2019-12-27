package com.blj.service;

import com.blj.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author BaiLiJun  on 2019/12/27
 */
@Service
@Slf4j
public class MQStockService {
    @Autowired
    private StockService stockService;
    /**
     * 监听库存消息队列，并消费
     * @param stockName
     */
    @RabbitListener(queues = RabbitMQConfig.STORY_QUEUE)
    public void decrByStock(String stockName) {
        log.info("库存消息队列收到的消息商品信息是：{}", stockName);
        /**
         * 调用数据库service给数据库对应商品库存减一
         */
        stockService.decrByStock(stockName);
    }
}

