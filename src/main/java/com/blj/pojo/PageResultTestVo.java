package com.blj.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author BaiLiJun on 2020/12/11 11:42 上午
 */
@Data
public class PageResultTestVo {

   private List<User> list;

   private Long total;

}
