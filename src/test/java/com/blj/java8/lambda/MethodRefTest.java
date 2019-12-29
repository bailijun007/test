package com.blj.java8.lambda;

import com.blj.pojo.User;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.function.*;

/**
 * 一：方法引用:若Lambda体中的内容有方法已经实现了，我们可以使用"方法引用"
 * (可以理解为方法引用是Lambda表达式的另外一种表现形式)
 * <p>
 * 主要有三种语法格式:
 * <p>
 * 对象:实例方法名
 * <p>
 * 类::静态方法名
 * <p>
 * 类::实例方法名
 * 注意:
 * 1.lambda体中调用方法的参数列表与返国值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致!
 * 2若Lambda参数列表中的第一参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName :: method
 *
 * 二。构造器引用
 * 语法：className::new
 *
 * 注意：需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致!
 *
 *三、数组引用:
 * Type[]::new;
 *
 *
 * @author BaiLiJun on 2019/12/22 0022
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MethodRefTest {


    @Test
    public void test1() {

        Consumer<Double> con = x -> System.out.println("今日消费了：" + x + "元！");
        con.accept(200D);
        //==>等价于下面这种写法
        Consumer<Double> con2 = System.out::println;
        con2.accept(300D);
        System.out.println(con2);
    }


    //对象:实例方法名
    @Test
    public void test2() {
        User user1 = new User();
        Supplier<String> sup = () -> user1.getName();
        String s = sup.get();
        System.out.println("s = " + s);

        //    等价于   ==>
        Supplier<String> sup2 = user1::getName;
        String s1 = sup2.get();
        System.out.println("s1 = " + s1);

    }

    //类::静态方法名
    @Test
    public void test3() {

        Comparator<Integer> com = (o1, o2) -> Integer.compare(o1, o2);

        //==>等价于
        Comparator<Integer> com2 = Integer::compare;

    }

    //类::实例方法名
    @Test
    public void test4() {

        BiPredicate<String,String> bp=(x,y)->x.equals(y);
        System.out.println(bp.test("a", "b"));

        BiPredicate<String,String> bp2=String::equals;
        System.out.println(bp2.test("b", "b"));

    }

    //构造器引用（无参）
    @Test
    public void test5() {
        Supplier<User> sup=()->new User();
        User user = sup.get();

        //构造器引用方式（无参）
        Supplier<User> sup2=User::new;
        User user1 = sup2.get();
        System.out.println(user1);

    }

    //构造器引用（有1个参数）
    @Test
    public void test6() {
        Function<Long,User> fun=(x)->new User(x);
        User user = fun.apply(1L);
        System.out.println(user);

        //构造器引用方式（有1个参数）
        Function<Long,User> fun2=User::new;
        User user1 = fun2.apply(2L);
        System.out.println("user1 = " + user1);

    }

    //构造器引用（有2个参数）
    @Test
    public void test7() {
        BiFunction<Long,String,User> fun2=User::new;
        User user1 = fun2.apply(2L,"zhangsan");
        System.out.println("user1 = " + user1);

    }


    @Test
    public void test8() {
        Function<Integer,String[]>fun=x->new String[x];
        String[] apply = fun.apply(10);
        System.out.println(apply.length);


        Function<Integer,String[]>fun2=String[]::new;
        System.out.println(fun2.apply(20).length);

    }

}
