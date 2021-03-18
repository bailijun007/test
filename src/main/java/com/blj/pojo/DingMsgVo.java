/**
 * @author zhangxiangqian
 * @date 2019/8/21 19:58
 * @since: 1.0-SNAPSHOT
 */
package com.blj.pojo;

import lombok.Data;

import java.util.Map;

@Data
public class DingMsgVo {
    //param跳转链接字段
    private String param;
    //OA字段
    private String title;//标题
    private String content;//内容
    private String message_url;//链接
    private String pc_message_url;//pc链接
    private String image;//图片链接
    private String file_count;//附件个数
    private String author;//作者
    private Map<String,String> formList;//表单列表
    private String num;//富文本
    private String unit;//富文本
    private String bgcolor;//背景颜色
    private String text;//头部内容

    //Text字段
    private String textMsg;

}
