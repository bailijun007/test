package com.blj.function;

/**
 * @author BaiLiJun on 2019/12/22 0022
 */
@FunctionalInterface
public interface MyFunction<T,R> {

    public R getValue(T t1,T t2);

}
