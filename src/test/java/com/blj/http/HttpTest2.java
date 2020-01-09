package com.blj.http;

import com.blj.util.HttpClientUtil;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author BaiLiJun  on 2020/1/2
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpTest2 {
    private static final Logger logger = LoggerFactory.getLogger(HttpTest2.class);


    @Test
    public void getNotify() {
        String sign = "MD5"
                + "c2c15783944468870203-0"
                + "1000"
                + "INR"
                + "tra15783107712093153123"
                +"success"
                + "pkriDZoEwB";
        String md5 = DigestUtils.md5DigestAsHex(sign.getBytes());

        logger.info("回调通知的md5:{}", md5);

    }


    @Test
    public void getSign() {
        String sign = "https://ex-fund.icocrop.io/api/callback/c2c/deposit/tradeSuccessSkip" +
                "https://ex-fund.icocrop.io/api/callback/c2c/deposit/notify" + "MD5"
                + "c2c15784679970202637-1003" + "1000" + "INR" + "lsb19841626045" + "pkriDZoEwB";
        String md5 = DigestUtils.md5DigestAsHex(sign.getBytes());

        System.out.println("获取到加密后的签名 = " + md5);
        logger.info("获取到加密后的签名:{}", md5);

    }

    @Test
    public void test1() {
        String url = "http://182.162.20.46:88/client/tracnce" + "?orderNo=15782991044216345" + "&customerId=0001" + "&orderCurrency=CNY" + "&orderAmount=700"
                + "&receiveUrl=www.baidu.com" + "&pickupUrl=www.baidu.com" + "&shopNo=291" + "&signType=MD5" + "&sign=pkriDZoEwB";

        System.out.println("重定向请求url为 = " + url);
        logger.info("重定向请求url为:{}", url);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        int codeValue = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getStatusCodeValue();
        System.out.println("codeValue = " + codeValue);

    }


}
