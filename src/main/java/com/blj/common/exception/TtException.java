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
public class TtException extends RuntimeException{

    private ExceptionEnums exceptionEnums;

}
