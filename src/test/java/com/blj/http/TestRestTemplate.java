package com.blj.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.blj.pojo.OkResponseEntity;
import com.blj.pojo.TickerData;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/3/18
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class TestRestTemplate {
    @Test
    public void test1() {
        String url = "http://115.144.238.29:90/client/tracnce?orderNo=c5880997143-135762957131874304&customerId=test1234&orderCurrency=CNY&orderAmount=121&receiveUrl=https://www.icocrop.io/v1/http/c2c/callback/money/in&pickupUrl=https://www.icocrop.io/v1/http/c2c/callback/web/pay&shopNo=356&signType=MD5&sign=sign=6dbfa98143c3edadd455b2308f2e9ef1";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        int codeValue = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getStatusCodeValue();
        System.out.println("codeValue = " + codeValue);

    }

    @Test
    public void test2() {
        String url = "http://api.zb.live/data/v1/ticker?market=btc_usdt";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<TickerData> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, TickerData.class);
        TickerData tickerData = responseEntity.getBody();
        System.out.println(tickerData.getTicker());

    }

    @Test
    public void test3() {
        String url = "https://www.okex.com/api/spot/v3/instruments/BTC-USDT/ticker";
//        String url = "http://api.zb.live/data/v1/ticker?market=btc_usdt";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
        Object okResponseEntity = responseEntity.getBody();
        System.out.println(okResponseEntity.toString());

    }

    @Test
    public void test4() {
        OkHttpClient client = new OkHttpClient();

//           String url = "http://api.zb.live/data/v1/ticker?market=btc_usdt";
//        String url = "https://www.okex.com/api/spot/v3/instruments/BTC-USDT/ticker";
        String url = "https://www.okex.com/api/spot/v3/instruments/ticker";
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);

        //同步调用,返回Response,会抛出IO异常
        try {
            Response response = call.execute();
            final ResponseBody responseBody = response.body();
            final String string = responseBody.string();
//            final OkResponseEntity entity = JSON.parseObject(string, OkResponseEntity.class);
//            System.out.println("entity = " + entity.toString());

            final List<OkResponseEntity> list = JSON.parseArray(string, OkResponseEntity.class);
            System.out.println("list = " + list);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 网上案例
     * 向目的URL发送post请求
     * @param url       目的url
     * @param params    发送的参数
     * @return ResultVO
     */
//    public static ResultVO sendPostRequest(String url, MultiValueMap<String, String> params){
//        RestTemplate client = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        HttpMethod method = HttpMethod.POST;
//        // 以表单的方式提交
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        //将请求头部和参数合成一个请求
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//        //执行HTTP请求，将返回的结构使用ResultVO类格式化
//        ResponseEntity<ResultVO> response = client.exchange(url, method, requestEntity, ResultVO.class);
//
//        return response.getBody();
//    }


}
