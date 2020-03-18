package com.blj.http;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author BaiLiJun  on 2020/3/18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestRestTemplate {
    @Test
    public void test1(){
        String url ="http://115.144.238.29:90/client/tracnce?orderNo=c6390105662-0&customerId=gte666&orderCurrency=INR&orderAmount=7000&receiveUrl=https://ex-fund.icocrop.io/api/callback/c2c/deposit/notify&pickupUrl=https://ex-fund.icocrop.io/api/callback/c2c/deposit/tradeSuccessSkip&shopNo=334&signType=MD5&sign=4c0ea28f230eabc87c71c0e0551ee580";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        int codeValue = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getStatusCodeValue();
        System.out.println("codeValue = " + codeValue);

    }

}
