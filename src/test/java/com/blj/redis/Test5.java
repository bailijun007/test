package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.PosLevelVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun  on 2019/12/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test5 {
    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Test
    public void test1() {
        HashOperations hashOperations = templateDB0.opsForHash();
        String hashKey = "BTC__BTC_USD";
        Object s = hashOperations.get("pc_pos_level", hashKey);
        if (null != s) {
            List<PosLevelVo> voList = JSON.parseArray(s.toString(), PosLevelVo.class);
            List<BigDecimal> collect = voList.stream().filter(vo -> vo.getMinAmt().compareTo(new BigDecimal(0.01)) <= 0 && vo.getMaxAmt().compareTo(new BigDecimal(1000)) >= 0)
                    .map(PosLevelVo::getMinHoldMarginRatio).collect(Collectors.toList());
            System.out.println(collect);
        }
    }


    @Test
    public void test2() {
        HashOperations hashOperations = templateDB0.opsForHash();
        String key = "asset";
        String hashKey = "BTC";
        Object s = hashOperations.get(key, hashKey);
        if (null != s) {
            Map mapType = JSON.parseObject(s.toString(), Map.class);
            for (Object obj : mapType.keySet()){
//                System.out.println("key为："+obj+"值为："+mapType.get(obj));
                if(obj.toString().equals("chainSymbolId")){
                    System.out.println("chainSymbolId值为:"+mapType.get(obj));
                }
            }
        }
    }

}
