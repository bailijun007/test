package com.blj.service.impl;

import com.blj.mapper.OrderMapper;
import com.blj.pojo.Order;
import com.blj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author BaiLiJun  on 2019/12/27
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void createOrder(Order order) {
        orderMapper.insert(order);
    }
}
