package com.blj.stream;

import com.blj.pojo.User;
import org.hibernate.validator.constraints.EAN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream的三个操作步骤:
 * 1.创建Stream
 * 2.中间操作
 * 3.终止操作(终端操作)
 *
 * @author BaiLiJun on 2019/12/22 0022
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class StreamApiTest {

    @Test
    public void test() {
        //1.可以通过Collection系列集合提供的stream()或parallelStream()
        List list = new ArrayList();
        Stream stream = list.stream();


        //2.通过Arrays中的静态方法stream()获取数组流
        User[] users = new User[10];
        Stream<User> stream1 = Arrays.stream(users);

        //3.通过Stream类中的静态方法of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc", "dd");


        //4.创建无限流
        //迭代
        Stream<Integer> stream3 = Stream.iterate(0, x -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        //4.创建无限流
        //生成
        Stream<Integer> stream4 = Stream.generate(() -> (int) (Math.random()*100));
        stream4.limit(5).forEach(System.out::println);


    }


}
