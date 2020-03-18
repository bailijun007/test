package com.blj.singleton;

/**
 * 单例模式：饿汉
 *
 * @author BaiLiJun  on 2020/3/13
 */
public class Singleton2 {

    private static Singleton2 singleton = new Singleton2();

    private Singleton2() {
        System.out.println("我是Singleton2的构造方法");
    }

    public  static Singleton2 getSingleton() {
        return singleton;
    }

}
