package com.blj.pojo.ehr;


import java.io.Serializable;

/**
 * PageBean
 *
 * @author slipkinem
 * @date 2018/9/5 - 下午6:08
 * @license hellobike
 * @description 所有返回值基类
 *
 * @Author: huanglin
 * @Date: 2018/9/19 下午2:26
 * @Version 2.0
 * 去掉 @Data 静态数据也会生成 get set 方法。
 *
 * @auther: liangyy
 * @Data 2018-09-30 11:21
 *
 * 静态创建方法。
 *
 *
 */
public class ResultBean<T> implements Serializable {

    private String errorMessage; // 错误消息

    private int errorCode; // 错误码

    private String errorDetail; // 消息的详情信息

    private T data;


    public static <T> ResultBean build() {
        return ResultBean.build(ExceptionEnum.SUCCESS);
    }

    public static <T> ResultBean build(ExceptionEnum exceptionEnum) {
        return new ResultBean(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public static <T> ResultBean<T> build(T data) {
        return ResultBean.build(ExceptionEnum.SUCCESS, data);
    }

    public static <T> ResultBean<T> build(ExceptionEnum exceptionEnum, T data) {
        return ResultBean.build(exceptionEnum, null, data);
    }

    public static <T> ResultBean<T> build(ExceptionEnum exceptionEnum, String errorDetail, T data) {
        ResultBean<T> resultBean = new ResultBean<T>(exceptionEnum.getCode(), exceptionEnum.getMessage(), errorDetail, data);
        return resultBean;
    }

    public static <T> ResultBean<T> build(int errorCode, String errorMessage) {
        ResultBean<T> resultBean = new ResultBean<T>(errorCode, errorMessage);
        return resultBean;
    }

    private ResultBean(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private ResultBean(int errorCode, String errorMessage, String errorDetail, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
        this.data = data;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public static void main(){

    }

}