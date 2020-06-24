package com.blj.common.advice;

import com.blj.common.exception.ExceptionEnums;
import com.blj.common.exception.ExceptionResult;
import com.blj.common.exception.TtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author BaiLiJun on 2020/1/7 0007
 */
@ControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(TtException.class)
    public ResponseEntity<ExceptionResult> exceptionHandle(TtException e) {
        //   ExceptionEnums enums = e.getExceptionEnums();
        ResponseEntity<ExceptionResult> responseEntity = ResponseEntity.status(e.getExceptionEnums().getCode()).body(new ExceptionResult(e.getExceptionEnums()));
        return responseEntity;
    }




}
