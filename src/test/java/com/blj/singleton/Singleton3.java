package com.blj.singleton;

/**
 * 双检锁/双重校验锁（DCL，即 double-checked locking）
 *
 * @author BaiLiJun  on 2020/3/13
 */
public class Singleton3 {

    //多线程环境下必须要加volatile，禁止指令重排
    private volatile static Singleton3 singleton;


    private Singleton3() {
        System.out.println("我是Singleton3的构造方法");
    }

    public static Singleton3 getSingleton() {
        if (null == singleton) {
            synchronized (Singleton3.class) {
                if (null == singleton) {
                    singleton = new Singleton3();
                }
            }
        }
        return singleton;
    }

}
