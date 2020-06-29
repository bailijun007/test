package com.blj.proxy.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.assertj.core.util.Lists;
import org.junit.rules.Stopwatch;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author bailj@linkstec.com
 * @date 2020/6/29 15:17
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    @Around("execution(* com.blj.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("进入 :{} 方法中" , pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());

        //获取参数，pre操作
        log.info("收到参数为：{}", Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed(pjp.getArgs());
        log.info("返回值为：{}", JSON.toJSONString(result));
        long end = System.currentTimeMillis();
        log.info("消耗时间：{}毫秒", (end - start));
        return result;
    }


}
