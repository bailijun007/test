package com.blj.messageFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;

/**
 * @author BaiLiJun  on 2020/5/28
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class MessageFormatTest {

    @Test
    public void test1() {
        String asset = "USDT";
        String symbol = "BTC_USDT";
        int intervalKey = 1;
        String redisKey = MessageFormat.format("candle:bb:{0}:{1}:{2}", asset, symbol, intervalKey);
       // 运行结果： redisKey = candle:bb:USDT:BTC_USDT:1
        System.out.println("redisKey = " + redisKey);
    }

}
