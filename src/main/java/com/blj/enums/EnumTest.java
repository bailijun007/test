package com.blj.enums;

/**
 * @author BaiLiJun on 2020/9/4 11:00 上午
 */
public class EnumTest {
    public static void main(String[] args) {

        Short key = InformationTypeEnum.BUSINESS_TYPE_HONOR.getKey();
        System.out.println("key = " + key);

        String value = InformationTypeEnum.BUSINESS_TYPE_INFO.getValue();
        System.out.println("value = " + value);

        int code = ExceptionEnumDemo.EXCEPTION_ENUM_DEMO.getCode();
        System.out.println("code = " + code);
        String msg = ExceptionEnumDemo.EXCEPTION_ENUM_DEMO.getMsg();
        System.out.println("msg = " + msg);

    }
}
