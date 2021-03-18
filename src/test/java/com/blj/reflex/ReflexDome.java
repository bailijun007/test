package com.blj.reflex;

import com.blj.pojo.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;

/**
 * 反射demo
 *
 * @author BaiLiJun on 2020/7/15 3:05 下午
 */
public class ReflexDome {
    public static void main(String[] args) {
        //类的静态属性class
        Class userClass= User.class;
        System.out.println("User");
        System.out.println("User的类加载器："+userClass.getClassLoader());
        System.out.println("User的全限定名："+userClass.getName());
        System.out.println("User的父类全限定名："+userClass.getSuperclass());
        System.out.println("User是否是接口类型："+userClass.isInterface());
        Class[] interfaces= userClass.getInterfaces();
        for (Class i:interfaces) {
            System.out.println("user的实现接口全限定名："+i.getName());
        }

        TypeVariable[] typeParam=userClass.getTypeParameters();
        for (TypeVariable variable:typeParam) {
            System.out.println("User的访问修饰符："+variable.getName());
        }
        System.out.println("------------------Field");
        Field[] fields=userClass.getFields();
        for (Field i :fields) {
            System.out.println("User的public字段信息："+i);
        }
        Field[] privateFields=userClass.getDeclaredFields();
        for (Field i:privateFields) {
            System.out.println("User的private字段信息："+i);
        }
        System.out.println("---------------Methods");
        Method[] methods=userClass.getMethods();
        for (Method i:methods ) {
            System.out.println("User的方法信息："+i);
        }
        System.out.println("---------------Constructor");
        Constructor[] constructors= userClass.getConstructors();
        for (Constructor i:constructors) {
            //通过newInstance()方法，可以构造一个对象
            System.out.println("User的构造方法："+i);
        }
    }


}
