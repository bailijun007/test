package com.blj.annotation;

import java.lang.annotation.*;

/**
 * MyAnnotation
 *
 * 自定义注解demo
 *
 * 参考文章：https://blog.csdn.net/xsp_happyboy/article/details/80987484
 *
 * @author bailj@linkstec.com
 * @date 2020/6/23 13:49
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface MyAnnotation {
    String name();

    int age() default 18;

    int[] score();

}
