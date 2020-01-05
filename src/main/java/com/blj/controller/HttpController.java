package com.blj.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author BaiLiJun on 2020/1/3 0003
 */
@Controller
@Slf4j
public class HttpController {


    @GetMapping(value = "/http", produces = "application/json;charset=utf-8")
    public void getDataByCityName() throws Exception {
        String uri = "http://wthrcdn.etouch.cn/weather_mini?city=深圳";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
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


    @GetMapping(value = "/doGetControllerTwo", produces = "application/json;charset=utf-8")
    public String doGetControllerTwo(@RequestParam("name") String name, @RequestParam("age") Integer age) {

        String s= "没想到["+name+"]"+"都"+age+"岁了！";
        System.out.println("s = " + s);
        return s;
    }

    @PostMapping(value = "/doPostController", produces = "application/json;charset=utf-8")
    public String doPostController(@RequestParam("name") String name, @RequestParam("age") Integer age) {

        String s= "没想到["+name+"]"+"都"+age+"岁了！";
        System.out.println("s = " + s);
        return s;
    }

}
