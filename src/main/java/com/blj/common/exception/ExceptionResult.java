package com.blj.common.exception;

import lombok.Data;

/**
 * @author BaiLiJun on 2020/1/7 0007
 */
@Data
public class ExceptionResult {
    private String message;
    private int code;
    private Long timestamp;

    public ExceptionResult(ExceptionEnums enums){
        this.code=enums.getCode();
        this.message=enums.getMsg();
        this.timestamp=System.currentTimeMillis();

    }
}
