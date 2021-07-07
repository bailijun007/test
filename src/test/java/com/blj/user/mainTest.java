package com.blj.user;

import com.blj.pojo.User;

/**
 * 三元表达式demo
 */
public class mainTest {
    public static void main(String[] args) {
        User user = new User(1L,"张三");
        String name = user.getName() == null ? "" : user.getName();
        System.out.println("name = " + name);

        int age = user.getAge() == null ? 0 : user.getAge();
        System.out.println("age = " + age);
    }
}
