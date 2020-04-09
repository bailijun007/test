package com.blj.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * http请求工具类,注意理解http请求中header的作用
 *
 * @author liuxg
 * @date 2016年2月19日 上午11:27:12
 */
public class HttpReqUtil {


    /**
     * get方式请求
     *
     * @param url
     * @return 服务器返回值
     * @throws IOException
     */
    public static Response get(String url) throws IOException {
        Request request = new Request.Builder().url(url)
                .build();
        Response response = new OkHttpClient().newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("请求有错：" + response);
        }
        return response;
    }

    /**
     * post方式请求
     *
     * @param url
     * @return 服务器返回值
     * @throws IOException
     */
    public static Response post(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder().url(url)
                .post(body).build();
        Response response = new OkHttpClient().newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("请求有错：" + response);
        }

        return response;

    }
}

