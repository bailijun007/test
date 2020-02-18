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
import java.util.Optional;
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
            for (Object obj : mapType.keySet()) {
//                System.out.println("key为："+obj+"值为："+mapType.get(obj));
                if (obj.toString().equals("chainSymbolId")) {
                    System.out.println("chainSymbolId值为:" + mapType.get(obj));
                }
            }
        }
    }

    @Test
    public void test3() {
        HashOperations hashOperations = templateDB0.opsForHash();
        String asset = "ETH";
        String hashKey = asset + "__" + asset + "_USD";
        Object s = hashOperations.get("pc_pos_level", hashKey);
        if (null != s) {
//            List<PosLevelVo> voList = JSON.parseArray(s.toString(), PosLevelVo.class);
//            Optional<BigDecimal> first = voList.stream().filter(vo -> vo.getMinAmt().compareTo(BigDecimal.ZERO) <= 0 && vo.getMaxAmt().compareTo(BigDecimal.ZERO) >= 0)
//                    .map(PosLevelVo::getMinHoldMarginRatio).findFirst();
//            System.out.println(first.orElse(BigDecimal.ZERO));


            List<PosLevelVo> voList = JSON.parseArray(s.toString(), PosLevelVo.class);
            Optional<BigDecimal> first = voList.stream().filter(vo -> {
                BigDecimal val = new BigDecimal(9999999);
                return vo.getMinAmt().compareTo(val) <= 0 && vo.getMaxAmt().compareTo(val) >= 0;
            }).peek(o -> {
                System.out.println(o);
            })
                    .map(PosLevelVo::getMinHoldMarginRatio).findFirst();
            System.out.println(first.orElse(BigDecimal.ZERO));

//            List<BigDecimal> list = voList.stream().filter(vo -> vo.getMinAmt().compareTo(new BigDecimal(0.00)) <= 0 && vo.getMaxAmt().compareTo(new BigDecimal(0.02)) >= 0)
//                    .map(PosLevelVo::getMinHoldMarginRatio).collect(Collectors.toList());
//
//            System.out.println(list.get(0));

        }

//        System.out.println("默认值 0" );
    }

}
