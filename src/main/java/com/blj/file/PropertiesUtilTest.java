package com.blj.file;


import com.blj.file.PropertiesUtil;

import java.util.Map;

public class PropertiesUtilTest {


    public static void main(String[] args) {
        //传入路径，初始化配置文件，
        PropertiesUtil util = new PropertiesUtil("E:/config/config.properties");
        Map<Object, Object> map = util.map;
        System.out.println(map);
        for (Object key : map.keySet()) {
            System.out.println("key: " + key + ", value: " + map.get(key));
            if (key.toString().equals("cr.cms.ip")) {
                //更新数据
                util.upDate("cr.cms.ip", "新名字2222","E:/config/config.properties","E:/config/properties");
                System.out.println(util.get("cr.cms.ip"));
            }
        }


        //读取数据
//            System.out.println(util.getString("time"));
//            System.out.println(util.get("name"));
//            System.out.println(util.getInt("age"));
//            System.out.println(util.getDouble("height"));
//            System.out.println(util.getString("blog_name"));
//            System.out.println(util.getBoolean("over_read"));

        //更新数据
//            util.upDate("name","新名字2222");
//            System.out.println(util.get("name"));

        //删除数据
//            util.delete("height");
        //增加数据
        util.add("height", "22222","E:/config/properties");

    }


}
