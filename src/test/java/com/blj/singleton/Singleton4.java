package com.blj.singleton;

/**
 * 登记式/静态内部类
 *
 * @author BaiLiJun  on 2020/3/13
 */
public class Singleton4 {

   private static class SingletonHolder{
       private  static final  Singleton4 INSTANCE=new Singleton4();
   }

   private Singleton4(){

   }

   public static final Singleton4 getInstance(){
       return SingletonHolder.INSTANCE;
   }

}
