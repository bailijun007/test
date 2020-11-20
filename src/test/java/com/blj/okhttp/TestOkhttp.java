package com.blj.okhttp;

import com.alibaba.fastjson.JSON;
import com.blj.pojo.OkResponseEntity;
import com.blj.util.HttpReqUtil;
import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BaiLiJun  on 2020/4/9
 */
//@SpringBootTest
//@ActiveProfiles("local")
//@RunWith(SpringRunner.class)
public class TestOkhttp {

    public static void main(String[] args) throws Exception {
        // testGet();
//        testPostJson();
        testCommonPost();

    }

    // post FromBody---表单提交 常用
    private static String testCommonPost() {
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("processInstanceId", "b4518726-140c-4e0f-ba77-7229c42c1145");

        FormBody.Builder builder = new FormBody.Builder();
        for (String key : paramsMap.keySet()) {
            //追加表单信息
            builder.add(key, paramsMap.get(key));
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody formBody = builder.build();
        Request request = new Request.Builder().url("http://10.69.18.95:8106/dingApproval/getApprovalInstanceDetails").post(formBody).build();
        Call call = okHttpClient.newCall(request);
       // 同步请求
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            String string = responseBody.string();
            System.out.println("string = " + string);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 异步请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                //请求失败的处理
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//            }
//        });

        return null;
    }

    // RequestBody--json数据提交
    private static String testPostJson() throws Exception {
        // String timeMills = String.valueOf(System.currentTimeMillis());

        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", 1);
        map.put("pageSize", 15);

        String content = JSON.toJSONString(map);

        System.out.println("content = " + content);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, content);
        Request request = new Request.Builder()
                .url("http://10.69.18.84:8080/pivot-ehr/portal/task/getApprovedTasks")
                .post(body)
                //.addHeader("content-type", "application/json")
                .addHeader("jwtToken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJwb3J0YWwiLCJpc3MiOiJwb3J0YWwiLCJleHAiOjE2Njg5Mjg0NzAsInVzZXJJZCI6IjIxMDUxIiwiaWF0IjoxNjA1ODU2NDcwfQ.K58Lx1YO3XRoqSfavNtz6J7xW_OwRLGrKy9P6tWDuU0")
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            ResponseBody responseBody = response.body();
            String string = responseBody.string();
            System.out.println("string = " + string);
        } else {
            System.out.println("报错 = " + response);
        }


        return null;
    }

    private static OkResponseEntity testGet() {
        OkHttpClient client = new OkHttpClient();

//           String url = "http://api.zb.live/data/v1/ticker?market=btc_usdt";
        String url = "https://www.okex.com/api/spot/v3/instruments/BTC-USDT/ticker";
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        Call call = client.newCall(request);

        //同步调用,返回Response,会抛出IO异常
        try {
            Response response = call.execute();
            ResponseBody responseBody = response.body();
            String string = responseBody.string();
            OkResponseEntity entity = JSON.parseObject(string, OkResponseEntity.class);
            System.out.println("entity = " + entity.toString());
            return entity;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}