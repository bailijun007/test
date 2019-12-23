package com.blj.stream;

import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream中间操作
 *
 * @author BaiLiJun on 2019/12/22 0022
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StreamApiTest2 {

    List<User> list = Arrays.asList(
            new User(1L, "zhangsan", 29, "123456"),
            new User(2L, "lisi", 25, "123456"),
            new User(3L, "zhaoliu", 25, "123456"),
            new User(4L, "wangerma", 34, "123456"),
            new User(5L, "zhangergou", 28, "123456"),
            new User(5L, "zhangergou", 28, "123456")
    );

    /**
     * 筛选与切片
     * filter- 接收Lambda ，从流中排除某些元素。
     * limit- 截断流，使其元素不超过给定数量.
     * skip(n) -跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个,则返回一个空流。与limit(n)互补
     * distinct- 筛选，通过流所生成元素的hashCode() 和equals() 去除重复元素
     */
    @Test
    public void testSkip() {
        list.stream()
                .filter(user -> user.getAge() > 24)
                .skip(3L)
                .forEach(System.out::println);

    }


    @Test
    public void testDistinct() {
        list.stream().filter(U -> U.getAge() > 25)
                .distinct()
                .forEach(System.out::println);
    }


    /**
     * 映射
     * map-接收 Lambda，将元素转换成其他形式或提取信息。接收一个个数作为参数，该函数会被应用到每个元素上，并将其映射成-个新的元素。
     * flatMap-接收一 个函数作为参数， 将流中的每个值都换成另-个渣， 然后把所有流连接成-个流
     */
    @Test
    public void testMap() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        list.stream().map(s -> s.toUpperCase()).forEach(System.out::println);

    }

    @Test
    public void testFlatMap() {

        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");

        list.stream().flatMap(StreamApiTest2::filterCharater)
                .forEach(System.out::println);
    }


    public static Stream<Character> filterCharater(String s) {

        List<Character> list = new ArrayList<>();

        for (Character c : s.toCharArray()) {
            list.add(c);

        }
        return list.stream();

    }


}
