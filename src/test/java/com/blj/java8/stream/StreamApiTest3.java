package com.blj.java8.stream;

import com.blj.pojo.User;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 终止操作(终端操作)：一次性执行全部内容， 即“情性求值”
 *
 * @author BaiLiJun on 2019/12/25 0025
 */
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class StreamApiTest3 {
    List<User> list = Arrays.asList(
            new User(1L, "zhangsan", 29, "123456"),
            new User(2L, "lisi", 25, "11111"),
            new User(3L, "zhaoliu", 25, "123456"),
            new User(4L, "wangerma", 34, "22222"),
            new User(5L, "zhangergou", 28, "123456"),
            new User(5L, "zhangergou", 28, "123456")
    );

    /**
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是 否至少匹配一个元素
     * noneMatch-检查 是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中的任意元素
     * count-返回流中元素的总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */
    @Test
    public void test1() {
        boolean b = list.stream().allMatch(u -> u.getId() / 2 == 0);
        System.out.println(b);

        boolean b1 = list.stream().anyMatch(u -> u.getName().length() > 4);
        System.out.println(b1);

        boolean b2 = list.stream().anyMatch(u -> u.getName().length() > 7);
        System.out.println(b2);

        Optional<User> user = list.stream().findFirst();
        System.out.println("第一个员工姓名：" + user.get().getName());

        //parallelStream 并行流 多个线程同时找 谁找到就先返回
        //Stream 串行流 单线程
        Optional<User> optional = list.parallelStream().filter(u -> u.getAge() > 25).findAny();
        System.out.println("任意一个年龄大于25的人 = " + optional.get().getName());

    }

    @Test
    public void test2() {
        long count = list.stream().count();
        System.out.println("count = " + count);

        Optional<User> user = list.stream().max((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()));
        System.out.println("年龄最大的人的信息：" + user.get());

        Optional<Integer> user2 = list.stream().map(u -> u.getAge()).min((u1, u2) -> u1.compareTo(u2));
        System.out.println("年龄最小的人的年龄：" + user2.get());

    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) 1 reduce(BinaryOperator)
     * 可以将流中元素反复结合起来， 得到一个值。
     */
    @Test
    public void test3() {
        Optional<Long> optional = list.stream()
                .map(user -> user.getId())
                .reduce(Long::sum);
        System.out.println(optional.get());

        Integer ageSum = list.stream()
                .map(u -> u.getAge())
                .reduce(0, Integer::sum);
        System.out.println("ageSum = " + ageSum);


        long sum = list.stream()
                .mapToLong(u -> u.getId()).sum();
        System.out.println(sum);
    }


    @Test
    public void test4() {
        Optional<String> first = list.stream().filter(user -> user.getAge() > 28).map(User::getName).findFirst();
        System.out.println(first.orElse("没有找到"));

    }


    /**
     * 收集
     * collect-将流转换为其他形式。
     * 接收一个 Collector接口的实现，用于给Stream中元素傲汇总的方法
     */
    @Test
    public void test5() {
        list.stream()
                .map(User::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("---------------------");

        list.stream()
                .map(User::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);

    }

    @Test
    public void test6() {
        //总数
        Long count = list.stream()
                .collect(Collectors.counting());
        System.out.println("count = " + count);

        //平均值
        Double avgAge = list.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println("avgAge = " + avgAge);


        //最大值，最大年龄人的信息
        Optional<User> maxAgeUser = list.stream().collect(Collectors.maxBy((o1, o2) -> o1.getAge().compareTo(o2.getAge())));
        System.out.println("年龄最大的人的信息：" + maxAgeUser.get());

        //最小值，最小年龄
        Optional<Integer> minAge = list.stream().map(User::getAge).collect(Collectors.minBy(Integer::compare));
        System.out.println("minAge = " + minAge.get());

        //按年龄分组
        Map<Integer, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getAge));
        System.out.println("按年龄分组 ： " + map);

        for (Integer age : map.keySet()) {
            System.out.println("年龄为：" + age + ",    的人有：" + map.get(age));
        }


        //多级分组，先按年龄分组，再按id分组
        Map<Integer, Map<Long, List<User>>> map1 = list.stream()
                .collect(Collectors.groupingBy(User::getAge,
                        Collectors.groupingBy(User::getId)));
        for (Integer integer : map1.keySet()) {

            System.out.println("年龄为：" + integer + "   ,并且再按id分组的人 =" + map1.get(integer));

        }

    }

    //分区
    @Test
    public void testPartitioningBy() {
        Map<Boolean, List<User>> map = list.stream().collect(Collectors.partitioningBy(user -> user.getAge() > 25));
        for (Boolean b : map.keySet()) {
            List<User> users = map.get(b);
            if (b) {
                System.out.println("年龄大于25的人有： " + users);
            } else {
                System.out.println("年龄小于25的人有： " + users);
            }

        }
    }



    //聚合 （通过年龄获取聚合数据）
    @Test
    public void test8() {

        IntSummaryStatistics collect = list.stream().collect(Collectors.summarizingInt(User::getAge));

        System.out.println("总数：" + collect.getCount());

        System.out.println("年龄总和：" + collect.getSum());

        System.out.println("年龄最大值：" + collect.getMax());

        System.out.println("年龄最小值：" + collect.getMin());

    }

    @Test
    public void testLongSummaryStatistics() {
        List<Long> startAndEndList = new ArrayList<>();
        startAndEndList.add(1563909600000L);
        startAndEndList.add(1563919800000L);
        startAndEndList.add(1111111111111L);
        LongSummaryStatistics collect = startAndEndList.stream().collect(Collectors.summarizingLong(Long::longValue));
        long startMs = collect.getMin();
        System.out.println("startMs = " + startMs);
        long endMs = collect.getMax();
        System.out.println("endMs = " + endMs);
    }

    //拼接字符串
    @Test
    public void test9() {

        String s = list.stream().map(User::getName)
                .collect(Collectors.joining(","));

        System.out.println("把所有姓名以逗号分隔连接起来：" + s);


        System.out.println("---------------");

        String s1 = list.stream().map(User::getName)
                .collect(Collectors.joining(",", "===", "==="));

        System.out.println("把所有姓名以自定义方式连接起来：" + s1);


        //再把字符串转成集合
        List<String> list = Arrays.asList(s.split(",")).stream().map(u -> u.trim()).collect(Collectors.toList());
        System.out.println("list = " + list);

    }

}