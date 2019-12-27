package com.blj.string;

import com.blj.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
     * 需要 commons-lang3 jar包
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

        List<Long> ids = list.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(ids.toString());
        String str = StringUtils.join(ids.toArray(), ",");
        System.out.println("str = " + str);

    }


    @Test
    public void test1() {
        String ids = "1,2,3,4,5,6";
        List<Long> list = Arrays.asList(ids.split(",")).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        list.forEach(System.out::println);
    }


    @Test
    public void test4() {
        String asset = "BTC,ETH,";
        List<String> assetList = Arrays.asList(asset.split(",")).stream().map(s -> s.trim()).collect(Collectors.toList());
        System.out.println(assetList);

    }
}
