package com.blj.enums;

/**
 * @author BaiLiJun on 2020/9/7 2:13 下午
 */
public enum  ExceptionEnumDemo {

    EXCEPTION_ENUM_DEMO(1234,"异常枚举demo"),

    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ExceptionEnumDemo() {
    }

    ExceptionEnumDemo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
