package com.blj.common.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

/**
 * 封装返回值
 *
 * @author BaiLiJun on 2020/1/7 0007
 */
@Data
public class ResponseResult<T> {

    private int code = 0;
    private T data;



    public ResponseResult(T data) {
        this.code = 0;
        this.data = data;
    }


    public ResponseResult(int code, T data) {
        this.code = code;
        this.data = data;
    }



}
