package com.blj.jmm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Volatile 代码演示可见性 + 原子性
 * <p>
 * 1.验证:volatile的可见性:
 * <p>
 * 1.1假如int number = 0;, number 变量之前根本没有添加volatile关键字修饰,没有可见性
 *
 * @author BaiLiJun on 2020/2/18 0018
 */

class MyNumber {
    volatile int number = 10;

    public void update() {
        this.number = 12;
    }

}

@SpringBootTest
@RunWith(SpringRunner.class)
public class VolatileDemo {


    @Test
    public void testVolatile() {
        MyNumber myNumber = new MyNumber();
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "************** come in");
                Thread.sleep(3000);
                myNumber.update();
                System.out.println(Thread.currentThread().getName() + "   update number ,number value:" + myNumber.number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        while (myNumber.number == 10) {
            //需要有一种通知机制告诉main线程， number已经修改为12， 跳出while
        }
        System.out.println("当前变量number的值为：" + myNumber.number);
        System.out.println(Thread.currentThread().getName() + "   mission is over");
    }

}

