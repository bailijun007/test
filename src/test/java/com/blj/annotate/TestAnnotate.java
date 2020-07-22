package com.blj.annotate;

import javax.persistence.ManyToOne;

/**
 * @author BaiLiJun on 2020/7/22 3:49 下午
 * 注释是不会执行，但是编译器读到之后会进行处理，转成 class 就凉凉了
 */
public class TestAnnotate {
    public static void main(String[] args) {
        String name = "沉默王二";
        // \u000dname="沉默王三";
        System.out.println(name);
    }
}
