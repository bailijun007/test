package com.blj.java8.stream;

import com.alibaba.fastjson.JSON;
import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author BaiLiJun on 2020/7/17 5:14 下午
 * <p>
 * Collectors.toList();
 * Collectors.toMap();
 * Collectors.toSet();
 * Collectors.toCollection();
 * Collectors.toConcurrentMap();
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class CollectorsTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 该方法先执行了一个归纳操作，然后再对归纳的结果进行 Function 函数处理输出一个新的结果
     */
    @Test
    public void testCollectingAndThen() {
        List<User> list = getList();
        // 比如我们将servers joining 然后转成大写
        String s = list.stream().map(User::getName).collect(Collectors.collectingAndThen(Collectors.joining(","), String::toUpperCase));
        System.out.println("s = " + s);

    }


    @Test
    public void testGroupingBy() {
        List<User> list = getList();

        // 按年龄分组
        Map<Integer, List<User>> listMap = list.stream().filter(u->u.getAge()!=null).collect(Collectors.groupingBy(User::getAge));
        System.out.println("按年龄分组 ： " + JSON.toJSONString(listMap));

        for (Integer key : listMap.keySet()) {
            List<User> list1 = listMap.get(key);
            List<User> userList = list1.stream().filter(u -> u.getCreateDate() != null)
                    .sorted((d1, d2) -> d2.getCreateDate().compareTo(d1.getCreateDate()))
                    .limit(3)
                    .collect(Collectors.toList());
            System.out.println("userList = " + userList);

        }

        // 如果我不想 Map 的 value 为 List 需要set怎么做呢
        Map<Integer, Set<User>> setMap = list.stream().collect(Collectors.groupingBy(User::getAge, Collectors.toSet()));
        System.out.println("按年龄分组 ： " + JSON.toJSONString(setMap));

        // 安全同步
        ConcurrentMap<Integer, Set<User>> setConcurrentMap = list.stream().collect(Collectors.groupingByConcurrent(User::getAge, Collectors.toSet()));
        System.out.println("按年龄分组 ： " + JSON.toJSONString(setConcurrentMap));


    }


    public List<User> getList() {
//        List<User> list = Arrays.asList(
//                new User(1L, "zhangsan", 29, "123456"),
//                new User(2L, "lisi", 25, "123456"),
//                new User(3L, "zhaoliu", 25, "123456"),
//                new User(4L, "wangerma", 34, "123456"),
//                new User(5L, "zhangergou", 28, "12345")
//        );
        List<User> list = userMapper.findAll();
        return list;
    }

}
