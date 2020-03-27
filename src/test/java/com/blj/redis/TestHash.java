package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.redis.pubsub.vo.BBSymbol;
import com.blj.redis.pubsub.vo.PcSymbol;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestHash {

    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Test
    public void test1(){
        String key="pc_contract";
        HashOperations opsForHash = templateDB0.opsForHash();
        Cursor<Map.Entry<String, Object>> curosr = opsForHash.scan(key, ScanOptions.NONE);

        List<PcSymbol> list = new ArrayList<>();
        while (curosr.hasNext()) {
            Map.Entry<String, Object> entry = curosr.next();
            Object o = entry.getValue();
            PcSymbol symbol = JSON.parseObject(o.toString(), PcSymbol.class);
            list.add(symbol);
        }
        for (PcSymbol bbSymbol : list) {
            System.out.println(bbSymbol);
        }
    }

}
