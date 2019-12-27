package com.blj.stock;

import com.blj.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2019/12/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StockTest {
    @Autowired
    private StockService stockService;

    @Test
    public void test1() {
        stockService.decrByStock("watch");

    }

    @Test
    public void test2() {
        stockService.decrByStock("小米");

    }

}
