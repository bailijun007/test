package com.blj.proxy;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

/**
 * 1.首先，要导入jar包:
 * SpringBoot 拦截器
 * 		<dependency>
 * 			<groupId>org.springframework.boot</groupId>
 * 			<artifactId>spring-boot-starter-aop</artifactId>
 * 		</dependency>
 *
 * 2.在启动类加上注解
 *  @EnableAspectJAutoProxy
 *
 * 3.Aspect切面类代码
 *      AspectConfig.java
 *
 * @author BaiLiJun on 2020/6/21
 */

@Aspect
@Slf4j
@Component
public class AspectConfig {

    @Pointcut("@within(org.springframework.stereotype.Service)")
    private void pointCutService() {

    }



    @Around("pointCutService()")
    public Object calaCostTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        log.info("进入 :{} 方法中" , joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        //获取参数，pre操作
        log.info("收到参数为：{}", Arrays.toString(joinPoint.getArgs()));

        //执行并得到结果
        Object result = joinPoint.proceed();

        log.info("返回值为：{}", JSON.toJSONString(result));

        long end = System.currentTimeMillis();
        log.info("消耗时间：{}毫秒", (end - start));
        return result;
    }



}
