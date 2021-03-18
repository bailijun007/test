package com.blj.map;

import lombok.Data;

import java.io.Serializable;

/**
 * @author BaiLiJun on 2020/11/23 3:22 下午
 */
@Data
public class MeikeAuthTokenData implements Serializable {
    private String tokenId;
    private String entCode;


}
