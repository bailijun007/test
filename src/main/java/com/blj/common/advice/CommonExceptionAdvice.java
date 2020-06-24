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
        String message = null;
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
        }else {
            message ="参数错误！";
        }

        return responseEntity;
    }

}
