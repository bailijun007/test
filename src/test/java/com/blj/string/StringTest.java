package com.blj.string;

import com.blj.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun on 2019/12/23 0023
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StringTest {

    /**
     * 某个数据库字段，存储的是逗号分隔的id，可能是Integer也可能是Long型的，
     * 比如：1,2,3等；需要转换成Long型的List或者Integer型的List
     */
    @Test
    public void test2() {
        String ids = "1,2,3,4,5,6";
        List<Long> listIds = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        System.out.println("listIds = " + listIds);
        listIds.add(7L);
        System.out.println("listIds = " + listIds);
        //        System.out.println(Arrays.toString(listIds .toArray()));
    }

    /**
     * 集合转string，并以逗号分割
     */
    @Test
    public void test3() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "zhangsan", 23, "123456"));
        list.add(new User(2L, "lisi", 19, "123456"));
        list.add(new User(3L, "wangwu", 26, "123456"));
        list.add(new User(4L, "zhaoliu", 28, "123456"));
        list.add(new User(5L, "wuming", 30, "123456"));

        //方式一：需要 commons-lang3 jar包
        List<Long> ids = list.stream().map(User::getId).collect(Collectors.toList());
        String str = StringUtils.join(ids.toArray(), ",");
        System.out.println("str = " + str);


        System.out.println("---------------------------");


        //方式2：需要 jdk1.8
        String s1 = list.stream().map(u -> String.valueOf(u.getId())).collect(Collectors.joining(","));
        System.out.println("s1 = " + s1);


    }


    //字符串转集合
    @Test
    public void test1() {
        String ids = "1,2,3,4,5,6";
        List<Long> list = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        list.forEach(System.out::println);
    }


    //字符串截取
    @Test
    public void test4() {
        String asset = "BTC,ETH,";
        List<String> assetList = Arrays.asList(asset.split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
        System.out.println(assetList);

    }

    //字符串拼接(集合转字符串)
    @Test
    public void test5() {
        String a = "aa";
        String b = "bb";
        String c = "cc";
        String d = "dd";
        String e = "ee";

        List<String> list = Arrays.asList(a, b, c, d, e);
        String s = String.join(",", a, b, c, d, e);
        System.out.println("s = " + s);


        System.out.println("-----------------");

        String join = String.join(",", list);
        System.out.println(join);

        System.out.println("-----------------");


        String s1 = list.stream()
                .collect(Collectors.joining(","));
        System.out.println("s1 = " + s1);

    }

    @Test
    public void test6() throws UnsupportedEncodingException {
        String  str ="aaaa中文的";
        byte[] gbkbt = str.getBytes("GB2312");
        byte[] utfbt = str.getBytes("utf-8");
        byte[] bt = str.getBytes();

        String  gbkstr= new String(gbkbt, "GB2312");  //string 与byte[] 转换时字符集要保持一致
        String  utfstr= new String(utfbt, "utf-8");
        str= new String(bt);

        System.out.println("gbkstr>>>>"+gbkstr);
        System.out.println("utfstr>>>>"+utfstr);
        System.out.println("str>>>>"+str);



    }


}
