package com.blj.common.advice;

import com.blj.common.exception.ExceptionEnums;
import com.blj.common.exception.ExceptionResult;
import com.blj.common.exception.TtException;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author BaiLiJun on 2020/1/7 0007
 *
 * 全局统一异常处理类：定义全局 异常Controller接管所有抛出的异常
 *
 * 对于应用级别的业务异常处理，我们可以通过注解 @ControllerAdvice 或 @RestControllerAdvice 来实现异常处理。
 * 但是上面的注解只能捕获处理应用级别的异常，例如 Controller 中抛出自定义的异常。却不能处理容器级别的异常，例如 Filter 中抛出的异常等。
 * 要想处理容器级别的异常，需要继承 BasicErrorController 类，重写 errorHtml 和 error 方法。或者实现 ErrorController 接口，
 * 起到和类 BasicErrorController 相似的作用
 *
 * 注意：后台的未被捕获的异常将从dao层到dervice层到controller层，然后被全局异常controller统一接管，封装之后返回给前台！
 *
 */
@RestControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(TtException.class)
    public ResponseEntity<ExceptionResult> exceptionHandle(TtException e) {
        //   ExceptionEnums enums = e.getExceptionEnums();
        ResponseEntity<ExceptionResult> responseEntity = ResponseEntity.status(e.getExceptionEnums().getCode()).body(new ExceptionResult(e.getExceptionEnums()));
        return responseEntity;
    }


    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ExceptionResult> handleException(ValidationException exception) {
        ExceptionResult result = new ExceptionResult();
        int code = 100102;
        ResponseEntity<ExceptionResult> responseEntity = ResponseEntity.status(code)
                .body(result);
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
        return responseEntity;
    }

}
