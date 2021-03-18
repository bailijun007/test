package com.blj.controller;

import com.blj.pojo.TOrder;
import com.blj.pojo.dto.OrderInputDto;
import com.blj.service.TOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author BaiLiJun on 2020/7/17 2:56 下午
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Resource
    private TOrderService tOrderService;


    @PostMapping("/save")
    public void save(OrderInputDto orderInputDto){
        if(StringUtils.isBlank(orderInputDto.getOrderName())){
            return;
        }
        TOrder order=new TOrder();
        BeanUtils.copyProperties(orderInputDto,order);
        tOrderService.insert(order);
    }

}
