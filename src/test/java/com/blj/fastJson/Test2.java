package com.blj.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.blj.pojo.PageResultTestVo;
import com.blj.pojo.User;
import com.blj.pojo.YongYouApproveRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author BaiLiJun on 2020/11/30 3:50 下午
 */
public class Test2 {

    public static void main(String[] args) {
        YongYouApproveRequest data = new YongYouApproveRequest();
        data.setUsercode("wb09908");

        data.setPageIndex(1);

        data.setPageSize(15);

        String requestData = JSON.toJSONString(data, SerializerFeature.DisableCircularReferenceDetect);

        System.out.println("requestData = " + requestData);
    }



}
