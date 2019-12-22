package com.blj.lambda;

import com.blj.function.MyFunction;
import com.blj.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.*;

/**
 * @author BaiLiJun on 2019/12/22 0022
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class lambdaTest {

    List<User> list = Arrays.asList(
            new User(1L, "zhangsan", 29, "123456"),
            new User(2L, "lisi", 25, "123456"),
            new User(3L, "zhaoliu", 25, "123456"),
            new User(4L, "wangerma", 34, "123456"),
            new User(5L, "zhangergou", 28, "123456")
    );


    @Test
    public void test1() {
        //如果年龄相同，则比较名字
        Collections.sort(list, (o1, o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                //年龄从小到大排序
                return o1.getAge().compareTo(o2.getAge());

                //年龄从大到小排序
//                return o2.getAge().compareTo(o1.getAge());
            }
        });

        for (User user : list) {
            System.out.println("user = " + user);
        }

    }


    @Test
    public void test2() {

        op(100L, 100L, (t1, t2) -> t1 + t2);

        op(100L, 100L, (t1, t2) -> t1 * t2);
    }


    public void op(Long x, Long y, MyFunction<Long, Long> myFunction) {
        Long value = myFunction.getValue(x, y);
        System.out.println(value);
    }


    /**
     * Consumer<T>
     * 消费型函数接口
     * void accept(T t);
     */
    @Test
    public void test3() {
        consumerTest(1000D, (d) -> System.out.println("买手机消费了：" + d + "元"));
    }


    public void consumerTest(Double d, Consumer<Double> consumer) {
        consumer.accept(d);
    }

    /**
     * Supplier<T>
     * 供给型函数接口demo
     * T get();
     */
    @Test
    public void test4() {
        List<Integer> list = supplierTest(10, () -> (int) (Math.random() * 100));
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    //需求:产生指定个数的整数，并放入集合中
    public List<Integer> supplierTest(int num, Supplier<Integer> supplier) {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {

            Integer integer = supplier.get();

            list.add(integer);

        }

        return list;
    }

    /**
     * Function<T,R>
     * 函数式接口
     * R apply(T t);
     */
    @Test
    public void functionTest() {
        String newStr = strHandle("asd", str -> str.toUpperCase());
        System.out.println("newStr = " + newStr);

        String newStr2 = strHandle("\t\t\t asd", str -> str.trim());
        System.out.println("newStr2 = " + newStr2);

        String newStr3 = strHandle("helloworld", str -> str.substring(5, 10));
        System.out.println("newStr3 = " + newStr3);

        String newStr4 = strHandle("hello world", str -> str.split(" ")[1]);
        System.out.println("newStr4 = " + newStr4);

    }

    //需求：用于处理字符串
    public String strHandle(String str, Function<String, String> fun) {
        String s = fun.apply(str);

        return s;
    }

    /**
     * Predicate<T>
     * 断言型函数式接口
     * boolean test(T t);
     */
    @Test
    public void predicateTest() {

        List<String> stringList = Arrays.asList("www", "baidu", "com", "http");

        List<String> list = filterStr(stringList, p -> p.length() > 3);
        for (String s : list) {
            System.out.println("s = " + s);
        }

    }

    //需求:将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();

        for (String s : list) {
            if (pre.test(s)) {
                strList.add(s);
            }
        }

        return strList;
    }

    /**
     * BiFunction<T, U, R>是Function<T,R> 的子接口
     * R apply(T t, U u);
     */
    @Test
    public void biFunctionTest(){
        String s = strHandle2("\t\t\t hello", "lambda", (str1, str2) -> {
            String trim = str1.trim();
            String s1 = str2.toUpperCase();
            return trim + s1;
        });
        System.out.println(s);
    }


    //需求：用于处理2个字符串操作
    public String strHandle2(String str1,String str2, BiFunction<String, String,String> fun) {
        String s = fun.apply(str1,str2);

        return s;
    }



}

