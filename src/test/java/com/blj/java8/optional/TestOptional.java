package com.blj.java8.optional;

import com.blj.pojo.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author BaiLiJun on 2020/7/23 10:48 上午
 */
public class TestOptional {

    public static void main(String[] args) {
        User u1 = new User( "a", 21,"123456");
        User u2 = new User( "b", 23,"123456");
        User u3 = new User("c", 19,"123456");
        User u4 = new User("d", 31,"123456");
        User u5 = new User("e", 28,"123456");

        List<User> userList = Arrays.asList(u1, u2, u3, u4, u5);
//        notRecommend(u1);
//         mapTestMethod1();
//        mapTestMethod2(u1);
//        flatMapTestMothod();
        filterTestMothod();

    }

    /**
     * 检查给定的Option值是否满足某些条件。如果满足则返回同一个Option实例，否则返回空Optional
     */
    private static void filterTestMothod() {
        Optional<String> name2 = Optional.of("abc");
        //System.out.println(name2.filter((value) -> value.length() > 5).orElse(null));
        System.out.println(name2.filter((value) -> value.length() > 2).orElse(null));
    }

    /**
     * public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
     * 如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional。
     * <p>
     * flatMap与map（Funtion）方法类似，区别在于传入方法的lambda表达式的返回类型。
     * map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
     */
    private static void flatMapTestMothod() {
        Optional<String> name2 = Optional.of("abc");
        Optional<String> s = name2.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(s.orElse("no value found"));
    }

    /**
     * map  是可能无限级联的, 比如再深一层, 获得用户名的大写形式
     */
    private static void mapTestMethod2(User u1) {
        //  Optional<User> user = Optional.of(null);
        Optional<User> user = Optional.ofNullable(u1);
        //System.out.println(user.map(u -> u.getName()).map(u -> u.toUpperCase()).orElse(null));

        Optional<String> s = user.map(u -> u.getName()).map(u -> u.toUpperCase());
//        System.out.println(s.orElseGet(() -> {
//            return createUserObject();
//        }));
    }

    /**
     * public<U> Optional<U> map(Function<? super T, ? extends U> mapper)
     * <p>
     * 如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，
     * 则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。
     */
    private static void mapTestMethod1() {
//          Optional<String> name2 = Optional.of("abc");
        Optional<String> name2 = Optional.ofNullable(null);
        Optional<String> s = name2.map((value) -> value.toUpperCase());
        System.out.println(s.orElse("no value found"));

    }

    //不推荐的使用方式
    private static void notRecommend(User u1) {
        Optional<User> userOptional = Optional.ofNullable(u1);
        if (userOptional.isPresent()) {
            System.out.println(userOptional.get());
        } else {
            System.out.println(Collections.emptyList());
        }
    }


    private static String createUserObject() {
        return "SUCCESS";
    }

}
