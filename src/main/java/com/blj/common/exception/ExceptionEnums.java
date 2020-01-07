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

    USER_NOT_BE_FIND(100101, "用户不存在"),


    ;
    private int code;
    private String msg;
}
