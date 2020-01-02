package com.blj.http;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpTest {

    @Test
    public void getDataByCityName() throws Exception {
        String uri = "http://wthrcdn.etouch.cn/weather_mini?city=" + "深圳";
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
//        System.out.println("strbody = " + strbody);
        int codeValue = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getStatusCodeValue();
        System.out.println("codeValue = " + codeValue);

//        Map map = JSONObject.parseObject(strbody, Map.class);
//        for (Object o : map.keySet()) {
//            System.out.println(o+", 值为："+map.get(o));
//        }
    }
}
