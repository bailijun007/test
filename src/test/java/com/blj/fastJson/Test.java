package com.blj.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blj.pojo.PageResultTestVo;
import com.blj.pojo.User;

import java.util.*;

/**
 * @author BaiLiJun on 2020/11/30 3:50 下午
 */
public class Test {

    public static void main(String[] args) {
        List<User> list = Arrays.asList(
                new User( "zhangsan", 20,"123456",true),
                new User("lisi", 25, "123456",true),
                new User("zhaoliu", 25, "123456",true),
                new User( "wangerma", 34, "123456",true),
                new User( "zhangergou", 28, "12345",false)
        );

//        list.

        PageResultTestVo pageResultTestVo = new PageResultTestVo();

        // 测试空集合，然后list1.size 会不会报空指针 --- 不会
        List<User> list1 = JSONObject.parseArray(JSON.toJSONString(Collections.emptyList()), User.class);
        pageResultTestVo.setList(list1);
        Long i = Long.parseLong(list1.size() + "");
        pageResultTestVo.setTotal(i);
        System.out.println("pageResultTestVo = " + pageResultTestVo);



    }



}
