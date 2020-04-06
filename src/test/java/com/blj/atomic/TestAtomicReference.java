package com.blj.atomic;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.blj.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.NewThreadAction;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author BaiLiJun  on 2020/4/5
 */
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest
public class TestAtomicReference {

    @Test
    public void testAtomicReference() {
        //原始user的值是tom 20
        User user = new User();
        user.setId(0L);
        user.setName("Tom");
        user.setAge(20);
        user.setPassword("123456");

        AtomicReference<User> userAtomicReference = new AtomicReference<>(user);
        AtomicStampedReference<User> atomicStampedReference = new AtomicStampedReference<>(user, 1);

        log.warn("===========以下是ABA问题的产生==========");
        new Thread(() -> {
            User user1 = new User();
            user1.setId(1L);
            user1.setName("Tom1");
            user1.setAge(21);
            user1.setPassword("123456");
            userAtomicReference.compareAndSet(user, user1);

            User user2 = new User();
            user2.setId(0L);
            user2.setName("Tom");
            user2.setAge(20);
            user2.setPassword("123456");
            userAtomicReference.compareAndSet(user1, user);
        }, "A").start();


        new Thread(() -> {
            try {
                //睡眠一秒钟保证A线程完成一次ABA问题的产生
//                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final User user1 = new User();
            user1.setId(2L);
            user1.setName("Tom2");
            user1.setAge(22);
            user1.setPassword("123456");
            final boolean b = userAtomicReference.compareAndSet(user, user1);
            System.out.println(Thread.currentThread().getName() + "\t" + b + userAtomicReference.get());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        log.warn("===========以下是ABA问题的解决==========");

        new Thread(() -> {
            User user1 = new User();
            user1.setId(1L);
            user1.setName("Tom1");
            user1.setAge(21);
            user1.setPassword("1234567");
            int stamp = atomicStampedReference.getStamp();
            log.info(Thread.currentThread().getName() + "\t第1次版本号是：{}", stamp);
            try {
                //先睡眠一秒钟保证D线程第一次拿到的值跟C线程拿到的值一样
//                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(user, user1, stamp, atomicStampedReference.getStamp() + 1);
            log.info(Thread.currentThread().getName() + "\t第2次版本号是：{}", atomicStampedReference.getStamp());

            User user2 = new User();
            user2.setId(0L);
            user2.setName("Tom");
            user2.setAge(20);
            user2.setPassword("123456");
            atomicStampedReference.compareAndSet(user1, user, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            log.info(Thread.currentThread().getName() + "\t第3次版本号是：{}", atomicStampedReference.getStamp());
        }, "C").start();


        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info(Thread.currentThread().getName() + "\t第1次版本号是：{}", stamp);
            try {
                //先睡眠3秒钟保证C线程完成ABA操作
//                Thread.sleep(1000);
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            User user4 = new User();
            user4.setId(8L);
            user4.setName("tom4");
            user4.setAge(22);
            user4.setPassword("123456");
            boolean booleanResult = atomicStampedReference.compareAndSet(user, user4, stamp, atomicStampedReference.getStamp() + 1);
            log.info("修改成功否？{}", booleanResult);
            log.info(Thread.currentThread().getName() + "\t版本号是：{}", atomicStampedReference.getStamp());
            log.info(Thread.currentThread().getName() + "\t当前实际最新值是：{}", atomicStampedReference.getReference());

        }, "D").start();

    }
}

