package com.blj.function;

/**
 * @author BaiLiJun on 2019/12/22 0022
 */
@FunctionalInterface
public interface MyFunction<T,R> {

    public R getValue(T t1,T t2);


    default Long calaMul(Long a,Long b){
        return a*b;
    }

    default Long calaAdd(Long a,Long b){
        return a+b;
    }

    public static Long calaSub(Long a,Long b){
        return a-b;
    }

    public static Long calaDiv(Long a,Long b){
        return a/b;
    }

}
