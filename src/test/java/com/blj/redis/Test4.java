package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.PcContractVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/27
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test4 {
    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Test
    public void test1() {
        Long number = templateDB0.opsForValue().increment("number", -1L);
        System.out.println("number = " + number);
    }

    @Test
    public void test2() {
        HashOperations hashOperations = templateDB0.opsForHash();
        Object o = hashOperations.get("pc_contract", "USDT__ETH_USD");
        String json = o.toString();
        System.out.println("json = " + json);
    }

    @Test
    public void test3() {
        HashOperations hashOperations = templateDB0.opsForHash();
        List<Object> list = new ArrayList<>();
        list.add("BTC__BTC_USD");
        list.add("BTC__pyc");
        list.add("USDT__ETH_USD");
        list.add("ETH__ETH_USD");
        list.add("BTC__pycp");
        list.add("USDT__pyc");

        List list1 = hashOperations.multiGet("pc_contract", list);
        System.out.println("list1 = " + list1);
    }


    //获取所有key 为：pc_contract 中所有的数据，并存入map中
    @Test
    public void test4() {
        HashOperations opsForHash = templateDB0.opsForHash();
        Cursor<Map.Entry<String, Object>> curosr = opsForHash.scan("pc_contract", ScanOptions.NONE);
        Map<String, PcContractVO> map = new HashMap<>();
        while (curosr.hasNext()) {
            Map.Entry<String, Object> entry = curosr.next();
//            System.out.println(entry.getKey()+":"+entry.getValue());

            Object o = entry.getValue();
            PcContractVO pcContractVO = JSON.parseObject(o.toString(), PcContractVO.class);
            map.put(entry.getKey(), pcContractVO);

            for (String s : map.keySet()) {
                System.out.println("key =" + s + "  ,值为：" + map.get(s));
            }

//            Map mapType = JSON.parseObject(str,Map.class);

        }
    }


    //获取所有key 为：pc_contract 中所有的数据，并存入list中
    @Test
    public void test5() {
        HashOperations opsForHash = templateDB0.opsForHash();
        Cursor<Map.Entry<String, Object>> curosr = opsForHash.scan("pc_contract", ScanOptions.NONE);

        List<PcContractVO> list = new ArrayList<>();
        while (curosr.hasNext()) {
            Map.Entry<String, Object> entry = curosr.next();
            Object o = entry.getValue();
            PcContractVO pcContractVO = JSON.parseObject(o.toString(), PcContractVO.class);
            list.add(pcContractVO);
        }
        for (PcContractVO pcContractVO : list) {
            System.out.println("pcContractVO = " + pcContractVO);
        }
    }

}
