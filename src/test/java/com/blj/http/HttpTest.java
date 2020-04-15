package com.blj.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class HttpTest {

    @Test
    public void getDataByCityName() throws Exception {
        String  str ="深圳";
        byte[] utfbt = str.getBytes("utf-8");
        String  utfstr= new String(utfbt, "utf-8");

        String uri = "http://wthrcdn.etouch.cn/weather_mini?city=" + utfstr;
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
        System.out.println("strbody = " + strbody);
        int codeValue = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getStatusCodeValue();
        System.out.println("codeValue = " + codeValue);

//        Map map = JSONObject.parseObject(strbody, Map.class);
//        for (Object o : map.keySet()) {
//            System.out.println(o+", 值为："+map.get(o));
//        }
    }

    @Test
    public void getDataByCityName2() throws Exception {
        String uri = "http://wthrcdn.etouch.cn/weather_mini?city=" + "深圳";
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity entity = new HttpEntity(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getBody();
        System.out.println("strbody = " + strbody);
        int codeValue = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class).getStatusCodeValue();
        System.out.println("codeValue = " + codeValue);

//        Map map = JSONObject.parseObject(strbody, Map.class);
//        for (Object o : map.keySet()) {
//            System.out.println(o+", 值为："+map.get(o));
//        }
    }



       /**
     * GET---有参测试 (方式一:手动在url后面加上参数)
     *
     */
    @Test
    public void doGetTestWayOne() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        StringBuffer params = new StringBuffer();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("name=" + URLEncoder.encode("&", "utf-8"));
            params.append("&");
            params.append("age=24");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://127.0.0.1:7890/doGetControllerTwo" + "?" + params);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            org.apache.http.HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }







}
