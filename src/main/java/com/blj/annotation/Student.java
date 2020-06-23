package com.blj.annotation;

/**
 * Student
 *
 * @author bailj@linkstec.com
 * @date 2020/6/23 14:00
 */
public class Student {
    @MyAnnotation(name = "TestMyAnnotationDemo",score = {90,99,80})
    public  void study(int times){
        for(int i = 0; i < times; i++){
            System.out.println("Good Good Study, Day Day Up!");
        }
    }
}
