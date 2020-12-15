package com.blj.date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author BaiLiJun on 2020/12/15 2:24 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestVo {

    // 创建时间
    private Date createTime;

    // 修改时间
    private Date updateTime;

    // 申请时间
    private String applyDateTime;

}
