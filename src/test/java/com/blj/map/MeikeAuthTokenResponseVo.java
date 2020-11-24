package com.blj.map;

import lombok.Data;

import java.io.Serializable;

/**
 * @author BaiLiJun on 2020/11/20 3:42 下午
 */
@Data
public class MeikeAuthTokenResponseVo implements Serializable {

    private String code;

    private String message;

    private Boolean success;

    private String entCode;

    private String tokenId;

    private MeikeAuthTokenData data;

}
