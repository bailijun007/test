package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.PosLevelVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@ActiveProfiles("local")
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test1 {


    @Resource(name = "templateDB0")
    private StringRedisTemplate template0;

    @Resource(name = "templateDB5")
    private StringRedisTemplate template5;


    /**
     * redis :
     * db: 0
     * redis key:pc_pos_level
     * hash key: ${asset}__${symbol}
     * 取 数组中的 minAmt <= ${volume} <= maxAmt 那条记录的 minHoldMarginRatio的值返回
     */
    @Test
    public void test1() {
        HashOperations hashOperations = template0.opsForHash();
        String hashKey = "BTC__BTC_USDT";
        Object s = hashOperations.get("pc_pos_level", hashKey);
        if (null != s) {
            List<PosLevelVo> voList = JSON.parseArray(s.toString(), PosLevelVo.class);
            List<BigDecimal> collect = voList.stream().filter(vo -> vo.getMinAmt().compareTo(new BigDecimal(0.01)) <= 0 && vo.getMaxAmt().compareTo(new BigDecimal(1000)) >= 0)
                    .map(PosLevelVo::getMinHoldMarginRatio).collect(Collectors.toList());
            System.out.println("collect="+collect);
        }
    }



}
