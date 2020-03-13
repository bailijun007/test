package com.blj.singleton;

/**
 * 单例模式：懒汉
 *
 * @author BaiLiJun  on 2020/3/13
 */
public class Singleton1 {

    private static Singleton1 singleton = null;

    private Singleton1() {

    }

    //懒汉式下需要加synchronized保证线程安全？
    public synchronized static Singleton1 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton1();
        }
        return singleton;
    }

}
