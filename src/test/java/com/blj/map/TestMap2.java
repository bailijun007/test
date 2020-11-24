package com.blj.map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blj.Application;
import okhttp3.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/4/16
 */

public class TestMap2 {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        String timeMills = String.valueOf(System.currentTimeMillis());
        System.out.println("timeMills = " + timeMills);


        StringBuilder stringBuilder = new StringBuilder("PanKmfeEX");
        stringBuilder.append(":");
        stringBuilder.append("AP191118WL88MMH");
        stringBuilder.append(":");
        stringBuilder.append(timeMills);
        /**
         * SHA256加密生成secret
         */
        String secret = DigestUtils.sha256Hex(stringBuilder.toString());
        // 3e572958a7a868e487c38583c0f11e3b5c2db9acd05690b2054b702e1fc1fba8
        System.out.println("secret = " + secret);

        Map<String, Object> map = new HashMap<>();
        map.put("appCode", "AP191118WL88MMH");
        map.put("secret", secret);
        map.put("timestamp", timeMills);
        String content = JSON.toJSONString(map);
        logger.info("==> content:{}", content);

        OkHttpClient client = new OkHttpClient();

            //MediaType mediaType = MediaType.parse("application/json");
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, content);
            Request request = new Request.Builder()
                    .url("https://uat.maycur.com/api/openapi/auth/login")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("response = " + response);
            if (response.isSuccessful()) {
                ResponseBody responseBody = response.body();
                String returnMsg = responseBody.string();
                MeikeAuthTokenResponseVo authTokenResponse = JSONObject.parseObject(returnMsg, MeikeAuthTokenResponseVo.class);
                logger.info("==> authTokenResponse:{}", JSON.toJSONString(authTokenResponse));
                if (authTokenResponse.getSuccess()) {
                    System.out.println("authTokenResponse.getTokenId() = " + authTokenResponse.getData().getTokenId());
                } else {
                    logger.error("meike sso send http error code={}, message={}", authTokenResponse.getCode(), authTokenResponse.getMessage());
                  //  throw new BusinessException(ExceptionEnum.MEIKE_SSO_ERROR);
                }

            } else {
                throw new IOException("请求有错：" + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
