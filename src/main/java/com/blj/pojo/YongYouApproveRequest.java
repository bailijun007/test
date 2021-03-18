package com.blj.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * YongYouApproveRequest
 *
 * @author gaol
 * @date 2020/01/19
 * @license hellobike
 * @description 用友任务审批Request
 */
@Data
public class YongYouApproveRequest implements Serializable {

    /**
     * 用户编码
     */
    String usercode;
    /**
     * 提交人Code
     */
    String submitusercode;
    /**
     * 提交开始时间
     */
    String begindate;
    /**
     * 提交结束时间
     */
    String enddate;
    /**
     * 类型 yc:全部  ycquotation:询报价  ycbid:招投标  ycmall:云采超市  ycsupmanage:供应商管理  ycpurchasecoop:采购协同
     */
    String categoryId;
    /**
     * 第几页
     */
    Integer pageIndex;
    /**
     * 每页数量
     */
    Integer pageSize;
    /**
     * 提交人
     */
    String submitUserName;
}
