package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.PosLevelVo;
import com.blj.pojo.User;
import com.blj.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun  on 2019/12/20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Test2 {
//    @Autowired
//    private List<StringRedisTemplate> tt1;
//    @Autowired
//    private List<RedisConnectionFactory> cf1;

//    @Autowired
//    private RedisUtil redisUtil;
    @Resource(name = "templateDB0")
    private StringRedisTemplate templateDB0;

    @Resource(name = "templateDB5")
    private StringRedisTemplate templateDB5;



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
    public void test4() {
        String s = templateDB5.opsForValue().get("markPrice:pc:current:BTC:BTC_USD");
        System.out.println("s = " + s);
    }

}
