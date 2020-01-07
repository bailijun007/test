package com.blj.common.util;

import lombok.Data;
import org.springframework.http.ResponseEntity;

/**
 * 封装返回值
 * @author BaiLiJun on 2020/1/7 0007
 */
@Data
public class ResponseResult<T> {

    private int code;
    private T data;

    public ResponseResult(ResponseEntity<T> responseEntity){
        this.code=responseEntity.getStatusCodeValue();
        this.data=responseEntity.getBody();
    }
}
