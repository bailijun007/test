package com.blj.pojo.ehr;

import lombok.Data;

/**
 * @author:hulukun
 * @date:2020-02-11 17:01
 * @description:
 */

@Data
public class BeiSenSSORequest {

    private String urlType;

//    @ApiModelProperty("必填 url类型")
//    private String type;


    private String sub;
}
