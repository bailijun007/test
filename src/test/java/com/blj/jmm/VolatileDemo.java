package com.blj.jmm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Volatile 代码演示可见性 + 原子性
 * <p>
 * 1.验证:volatile的可见性:
 * 1.1假如int number = 0;, number 变量之前根本没有添加volatile关键字修饰,没有可见性
 * 1.2添加了volatile，可以解决可见性问题。
 * <p>
 * 2验证volatile不保证原子性
 * 2.1原子性指的是什么意思?
 * 不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整
 * 要么同时成功，要么同时失败。.
 * 2.2是否可以保证原子性? .
 *
 * @author BaiLiJun on 2020/2/18 0018
 */

class MyNumber {
    volatile int number = 0;

    public void update() {
        this.number = 12;
    }

    //请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus() {
        this.number++;
    }


}

@SpringBootTest
@RunWith(SpringRunner.class)
public class VolatileDemo {

    //不保证原子性
    @Test
    public void test() {
        MyNumber myNumber = new MyNumber();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少?
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value =" + myNumber.number);
    }

    //volatile可以保证可见性，及时通知其它线程， 主物理内存的值已经被修改。
    @Test
    public void seeOkByVolatile() {
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

        while (myNumber.number == 0) {
            //需要有一种通知机制告诉main线程， number已经修改为12， 跳出while
        }
        System.out.println("当前变量number的值为：" + myNumber.number);
        System.out.println(Thread.currentThread().getName() + "   mission is over");
    }

}

