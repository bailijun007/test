package com.blj.common.advice;

import com.blj.common.exception.ExceptionResult;
import com.blj.common.exception.TtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author BaiLiJun on 2020/1/7 0007
 * <p>
 * 全局统一异常处理类：定义全局 异常Controller接管所有抛出的异常
 * <p>
 * 对于应用级别的业务异常处理，我们可以通过注解 @ControllerAdvice 或 @RestControllerAdvice 来实现异常处理。
 * 但是上面的注解只能捕获处理应用级别的异常，例如 Controller 中抛出自定义的异常。却不能处理容器级别的异常，例如 Filter 中抛出的异常等。
 * 要想处理容器级别的异常，需要继承 BasicErrorController 类，重写 errorHtml 和 error 方法。或者实现 ErrorController 接口，
 * 起到和类 BasicErrorController 相似的作用
 * <p>
 * 注意：后台的未被捕获的异常将从dao层到dervice层到controller层，然后被全局异常controller统一接管，封装之后返回给前台！
 */
@Slf4j
@RestControllerAdvice
public class CommonExceptionAdvice {

    // 自定义抛出异常全局处理
    @ExceptionHandler(TtException.class)
    public ExceptionResult exceptionHandle(TtException e) {
        ExceptionResult result=new ExceptionResult();
        result.setTimestamp(System.currentTimeMillis());
        result.setCode(e.getExceptionEnums().getCode());
        result.setMessage(e.getExceptionEnums().getMsg());
        return result;
    }


    // 全局异常信息
    @ExceptionHandler(value = Exception.class)
    public void handlerGlobalException(Exception e) {
        log.error("系统发生未知异常，具体异常信息为：{}", e.toString());
        e.printStackTrace();
    }


    // 校验异常全局处理
    @ExceptionHandler({ValidationException.class})
    public ExceptionResult handleException(ValidationException exception) {
        ExceptionResult result = new ExceptionResult();
        int code = 100102;
        result.setTimestamp(System.currentTimeMillis());
        result.setCode(code);
        String message = "参数错误！";
        if (exception instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                message = item.getMessage();
                if (!StringUtils.isEmpty(message)) {
                    break;
                }
            }
            result.setMessage(message);
        }
        return result;
    }

}
