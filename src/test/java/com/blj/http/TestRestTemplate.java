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
        String url ="http://115.144.238.29:90/client/tracnce?orderNo=c5880997143-135762957131874304&customerId=test1234&orderCurrency=CNY&orderAmount=121&receiveUrl=https://www.icocrop.io/v1/http/c2c/callback/money/in&pickupUrl=https://www.icocrop.io/v1/http/c2c/callback/web/pay&shopNo=356&signType=MD5&sign=sign=6dbfa98143c3edadd455b2308f2e9ef1";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        int codeValue = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getStatusCodeValue();
        System.out.println("codeValue = " + codeValue);

    }

}
