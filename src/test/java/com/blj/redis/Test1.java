package com.blj.redis;

import com.alibaba.fastjson.JSON;
import com.blj.mapper.PcAccountMapper;
import com.blj.pojo.PosLevelVo;
import com.blj.pojo.User;
import com.blj.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
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
public class Test1 {


    @Autowired
    private RedisUtil redisUtil;

//        @Resource(name = "template0")
//    private StringRedisTemplate template0;
//
//
//    @Resource(name = "template5")
//    private StringRedisTemplate template5;


    /**
     * redis :
     * db: 0
     * redis key:pc_pos_level
     * hash key: ${asset}__${symbol}
     * 取 数组中的 minAmt <= ${volume} <= maxAmt 那条记录的 minHoldMarginRatio的值返回
     */
    @Test
    public void test1() {
        redisUtil.setDataBase(0);
        StringRedisTemplate template = redisUtil.getRedisTemplate();

        HashOperations hashOperations = template.opsForHash();
        String hashKey = "BTC__BTC_USD";
        Object s = hashOperations.get("pc_pos_level", hashKey);
        if (null != s) {
            /*
             *json array转java List 需要fastjson jar包
             *
             * 常用fastjson 用法：
             *      1：fastjson List转JSONArray
             *      List<T> list = new ArrayList<T>();
             *      JSONArray array= JSONArray.parseArray(JSON.toJSONString(list))；
             *
             *      2.fastjson  JSONArray转List
             *      JSONArray array = new JSONArray();
             *       List<T> list = JSONObject.parseArray(array.toJSONString(), T.class);
             *
             *       3.fastjson  字符串转List
             *      String str = "";
             *      List<T> list = JSONObject.parseArray(str,T.class);
             *
             *      4.fastjson  字符串转Map
             *      String str = "";
             *      Map mapType =  JSONObject.parseObject(str,Map.class);
             *      for (Object obj : mapType.keySet()){
             *        System.out.println("key为："+obj+"值为："+mapType.get(obj));
             *       }
             */
            List<PosLevelVo> voList = JSON.parseArray(s.toString(), PosLevelVo.class);
            List<BigDecimal> collect = voList.stream().filter(vo -> vo.getMinAmt().compareTo(new BigDecimal(0.01)) <= 0 && vo.getMaxAmt().compareTo(new BigDecimal(1000)) >= 0)
                    .map(PosLevelVo::getMinHoldMarginRatio).collect(Collectors.toList());
            System.out.println(collect);
        }
    }


    /**
     * 某个数据库字段，存储的是逗号分隔的id，可能是Integer也可能是Long型的，
     * 比如：1,2,3等；需要转换成Long型的List或者Integer型的List
     */
    @Test
    public void test2() {
        String ids = "1,2,3,4,5,6";
        List<Long> listIds = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        System.out.println("listIds = " + listIds);
        listIds.add(7L);
        System.out.println("listIds = " + listIds);
        //        System.out.println(Arrays.toString(listIds .toArray()));
    }

    /**
     * 需要 commons-lang3 jar包
     * 集合转string，并以逗号分割
     */
    @Test
    public void test3() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "zhangsan", 23, "123456"));
        list.add(new User(2L, "lisi", 19, "123456"));
        list.add(new User(3L, "wangwu", 26, "123456"));
        list.add(new User(4L, "zhaoliu", 28, "123456"));
        list.add(new User(5L, "wuming", 30, "123456"));

        List<Long> ids = list.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(ids.toString());
        String str = StringUtils.join(ids.toArray(), ",");
        System.out.println("str = " + str);

    }


    @Test
    public void test4() {
        redisUtil.setDataBase(5);
        StringRedisTemplate template = redisUtil.getRedisTemplate();
//        String asset="BTC";
//        String symbol="BTC_USD";
        String s = template.opsForValue().get("markPrice:pc:current:BTC:BTC_USD");
        System.out.println("s = " + s);
    }

}
