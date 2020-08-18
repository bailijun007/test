package com.blj.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author BaiLiJun on 2020/1/7 0007
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ExceptionEnums {

    SUCCESS(0, "success"),

    DEFAULT_ERROR(100100,"服务器忙！"),
    USER_NOT_BE_FIND(100101, "用户不存在"),
    PARAM_ERROR(100102, "参数错误！"),


    ;
    private int code;
    private String msg;
}
