package com.blj.proxy.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;


/**
 * @author bailj@linkstec.com
 * 1.首先，要导入jar包:
 * SpringBoot 拦截器
 * 		<dependency>
 * 			<groupId>org.springframework.boot</groupId>
 * 			<artifactId>spring-boot-starter-aop</artifactId>
 *		</dependency>
 *
 * 2.在启动类加上注解
 *  @EnableAspectJAutoProxy
 *
 * 3.Aspect切面类代码
 *      AspectConfig.java
 *
 *
 * @date 2020/6/29 15:17
 */
@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    // @Pointcut("@within(org.springframework.stereotype.Service)")
    @Around("execution(* com.blj.controller.*.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        log.info("==> 进入:{}方法中" , pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName()+"()");

        // 获取请求头信息
         //getRequestHeaderInfo();

        //获取参数，pre操作
        log.info("==> 收到参数为:{}", Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed(pjp.getArgs());
        log.info("==> 返回值为:{}", JSON.toJSONString(result));
        long end = System.currentTimeMillis();
        log.info("==> 消耗时间:{}毫秒", (end - start));
        return result;
    }


    /**
     * 获取请求头信息
     */
    private void getRequestHeaderInfo(){
        // 在springAOP里面获取request和response对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        log.info("----------request.getRequestURI()="+ request.getRequestURI()+"---------------");

        // TODO 注意:request.getQueryString()只能获取到get请求方式到参数
        log.info("----------request.getQueryString()="+ request.getQueryString()+"---------------");

        // TODO 注意: request.getParameterNames()只能获取到get请求方式到参数
        Enumeration<String> parameterNames = request.getParameterNames();
        while(parameterNames.hasMoreElements()){
            log.info("----------parameterNames.nextElement()="+ parameterNames.nextElement().toString() +"---------------");
        }

        Enumeration<String> names =  request.getHeaderNames();
        while (names.hasMoreElements()) {
            log.info("----------request.getHeaderNames().nextElement()="+ names.nextElement().toString()+"---------------");
        }

        log.info("----------request.getHeader(Content-Type)="+ request.getHeader("Content-Type")+"---------------");

        log.info("----------request.getHeader(host)="+ request.getHeader("host")+"---------------");


    }

}
