package com.blj.jmm;

/**
 * @author BaiLiJun  on 2020/2/18
 */
class MyData {
    volatile int number = 0;


    //请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public void addPlusPlus() {
       number++;
    }


}

public class VolatileDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <=1000; j++) {
                    myData.addPlusPlus();
                }
            },String.valueOf(i)).start();

        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得最终的结果值看是多少?
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t finally number value =" + myData.number);
    }
}
