package com.blj.fastJson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blj.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2020/3/13
 */
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
public class TestFastJson {

    @Test
    public void test1() {
        String s = "[1483200240000,956.54,956.54,956.54,956.54,0.48375944]";
        JSONArray ja = JSON.parseArray(s);
        System.out.println("分钟时间戳：" + ja.getLong(0));
        System.out.println("开盘价：" + ja.getBigDecimal(1));
        System.out.println("最高价：" + ja.getBigDecimal(2));
        System.out.println("最低价：" + ja.getBigDecimal(3));
        System.out.println("收盘价：" + ja.getBigDecimal(4));
        System.out.println("总数：" + ja.getBigDecimal(5));
    }

    @Test
    public void test2() {
        String s = "[0,1483200240000,956.54,956.54,956.54,956.54,0.48375944]";
        JSONArray ja = JSON.parseArray(s);
        System.out.println("是否需要：" + ja.getInteger(0));
        System.out.println("分钟时间戳：" + ja.getLong(1));
        System.out.println("开盘价：" + ja.getBigDecimal(2));
        System.out.println("最高价：" + ja.getBigDecimal(3));
        System.out.println("最低价：" + ja.getBigDecimal(4));
        System.out.println("收盘价：" + ja.getBigDecimal(5));
        System.out.println("总数：" + ja.getBigDecimal(6));
    }

    /**
     * 进来使用fastjson的ToJsonString方法代替java的toString方法
     */
    @Test
    public void testToJsonString() {
        User user = new User();
        user.setId(0L);
        user.setName("testToJsonString");
        user.setAge(0);
        user.setPassword("123456");
        user.setCreateDate(LocalDate.now());
        user.setCreateTime(LocalDateTime.now());
        String jsonString = JSON.toJSONString(user);
        log.info("jsonString={}", jsonString);
    }

    /**
     * ParseObject
     */
    @Test
    public void testParseObject() {
        User user = new User();
        user.setId(0L);
        user.setName("testToJsonString");
        user.setAge(0);
        user.setPassword("123456");
        user.setCreateDate(LocalDate.now());
        user.setCreateTime(LocalDateTime.now());
        String jsonString = JSON.toJSONString(user);
        User user1 = JSON.parseObject(jsonString, User.class);
        log.info("JSON.parseObject={}", user1);
    }

    /**
     * ParseArray
     */
    @Test
    public void testParseArray() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User();
            user.setId(Long.parseLong(i + ""));
            user.setName("testParseArray" + i);
            user.setAge(i);
            user.setPassword("123456");
            user.setCreateDate(LocalDate.now());
            user.setCreateTime(LocalDateTime.now());
            list.add(user);
        }

        String jsonString = JSON.toJSONString(list);
        List<User> userList = JSON.parseArray(jsonString, User.class);
        log.info("JSON.parseArray={}", userList);
    }

    /**
     * fastjson 解析List<Map>格式数据
     */
    @Test
    public void testListMap() {
        String json = "{\n" +
                "\t\"topcqc\": {\n" +
                "\t\t\"high\": \"0.037655\",\n" +
                "\t\t\"vol\": \"1350209.5\",\n" +
                "\t\t\"last\": \"0.032021\",\n" +
                "\t\t\"low\": \"0.032013\",\n" +
                "\t\t\"buy\": \"0.032022\",\n" +
                "\t\t\"sell\": \"0.034877\"\n" +
                "\t},\n" +
                "\t\"kanqc\": {\n" +
                "\t\t\"high\": \"0.015547\",\n" +
                "\t\t\"vol\": \"1685173\",\n" +
                "\t\t\"last\": \"0.015135\",\n" +
                "\t\t\"low\": \"0.015074\",\n" +
                "\t\t\"buy\": \"0.015133\",\n" +
                "\t\t\"sell\": \"0.015139\"\n" +
                "\t}\n" +
                "}";

        JSONObject jsonObject = JSON.parseObject(json);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        }
    }

    /**
     *  JSON.parseArray 解析
     *   [
     * 	["tBTCUSD", 7265.1, 11.099559480000002, 7265.9, 20.18206469, -67.7, -0.0092, 7268.4, 7108.48189697, 7365.7, 7117],
     * 	["tLTCUSD", 46.165, 2087.26285921, 46.166, 2573.5900832400002, -0.109, -0.0024, 46.143, 32150.06219219, 46.854, 44.742]
     * 	]
     */
    @Test
    public void testListMap2() {
        String json = "[\n" +
                "\t[\"tBTCUSD\", 7265.1, 11.099559480000002, 7265.9, 20.18206469, -67.7, -0.0092, 7268.4, 7108.48189697, 7365.7, 7117],\n" +
                "\t[\"tLTCUSD\", 46.165, 2087.26285921, 46.166, 2573.5900832400002, -0.109, -0.0024, 46.143, 32150.06219219, 46.854, 44.742]\n" +
                "]";

        JSONArray objects = JSON.parseArray(json);
        for (Object object : objects) {
            JSONArray jsonArray = JSON.parseArray(object.toString());
            System.out.println("symbol=" + jsonArray.getString(0) + ",\t lastPrice ="+jsonArray.getBigDecimal(7));
        }
    }

    /**
     *  [
     *     {
     *         "id": 140520649,
     *             "price": "172.40000000",
     *             "qty": "0.12239000",
     *             "quoteQty": "21.10003600",
     *             "time": 1587210209544,
     *             "isBuyerMaker": true,
     *             "isBestMatch": true
     *     }
     * ]
     */
    @Test
    public void testParseArray2(){
        String json=" [\n" +
                "    {\n" +
                "        \"id\": 140520649,\n" +
                "            \"price\": \"172.40000000\",\n" +
                "            \"qty\": \"0.12239000\",\n" +
                "            \"quoteQty\": \"21.10003600\",\n" +
                "            \"time\": 1587210209544,\n" +
                "            \"isBuyerMaker\": true,\n" +
                "            \"isBestMatch\": true\n" +
                "    }\n" +
                "]";
        JSONArray objects = JSON.parseArray(json);

        for (Object object : objects) {
            System.out.println("object = " + object);
        }



    }
}
