package com.blj.singleton;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BaiLiJun  on 2020/3/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SingletonTest {

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> Singleton1.getSingleton(), String.valueOf(i + 1)).start();
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> Singleton2.getSingleton(), String.valueOf(i + 1)).start();
        }
    }


    @Test
    public void test3() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> Singleton3.getSingleton(), String.valueOf(i + 1)).start();
        }
    }

    @Test
    public void test4() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> Singleton4.getInstance(), String.valueOf(i + 1)).start();
        }
    }


}
