package com.blj.annotation;

import java.lang.reflect.Method;

/**
 * TestMyAnnotation
 *
 * @author bailj@linkstec.com
 * @date 2020/6/23 13:56
 */
public class TestMyAnnotation {



    public static void main(String[] args) {
        Student student = new Student();
        student.study(10);

        try {
            // 通过反射读取注解信息
            Class stuClass = Class.forName("com.blj.annotation.Student");

            // 说明一下，这里形参不能写成Integer.class，应写为int.class
            Method stuMethod = stuClass.getMethod("study", int.class);

            if (stuMethod.isAnnotationPresent(MyAnnotation.class)) {
                System.out.println("Student类上配置了MyAnnotation注解！");

                // 获取该元素上指定类型的注解
                MyAnnotation annotation = stuMethod.getAnnotation(MyAnnotation.class);
                System.out.println("name="+annotation.name()+",age="+annotation.age()+",score="+annotation.score());
            }else {
                System.out.println("Student类上没有配置MyAnnotation注解！");
            }

        }catch (Exception e){
           e.printStackTrace();
        }


    }
}
