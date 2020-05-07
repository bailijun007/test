package com.blj.mapper.bootTest2;

import com.blj.pojo.Order;
import com.blj.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/26
 */
public interface SecondOrderMapper {

    List<Order> queryList(Map<String, Object> map);

    Order queryOne(Map<String, Object> map);

    void insert(Order order);

    List<Order> findAll();
}
