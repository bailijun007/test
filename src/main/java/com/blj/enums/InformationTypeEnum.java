package com.blj.enums;

/**
 * 描述:
 *
 * @Author zhangxd
 * @Date 15:34 2019-11-15
 */
public enum InformationTypeEnum {

    BUSINESS_TYPE_RULES("规章制度", (short)1),
    BUSINESS_TYPE_LESSON("学习课程", (short)2),
    BUSINESS_TYPE_NOTICE("资讯中心", (short)3),
    BUSINESS_TYPE_INFO("新闻中心", (short)4),
    BUSINESS_TYPE_HONOR("荣誉殿堂", (short)5)

    ;

    private Short key;

    private String value;

    InformationTypeEnum(String value, Short key) {
        this.value = value;
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public Short getKey() {
        return key;
    }

}
