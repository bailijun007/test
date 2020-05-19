package com.blj.mapper;

import com.blj.pojo.Order;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/26
 */
public interface OrderMapper  {

    List<Order> queryList(Map<String,Object> map);

    Order queryOne(Map<String,Object> map);

    void insert(Order order);
}
