package com.blj.stream;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 终止操作(终端操作)：一次性执行全部内容， 即“情性求值”
 *
 * @author BaiLiJun on 2019/12/25 0025
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StreamApiTest3 {
    List<User> list = Arrays.asList(
            new User(1L, "zhangsan", 29, "123456"),
            new User(2L, "lisi", 25, "123456"),
            new User(3L, "zhaoliu", 25, "123456"),
            new User(4L, "wangerma", 34, "123456"),
            new User(5L, "zhangergou", 28, "123456"),
            new User(5L, "zhangergou", 28, "123456")
    );

    /**
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是 否至少匹配- -个元素
     * noneMatch- -检查 是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中的任意元素
     * count-返 回流中元素的总个数
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
}
