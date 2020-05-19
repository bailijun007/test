package com.blj.service.impl;

import com.blj.mapper.StockMapper;
import com.blj.pojo.Stock;
import com.blj.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/27
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class StockServiceImpl implements StockService {
    @Autowired
    private StockMapper stockMapper;

    @Override
    public void decrByStock(String stockName) {
        Map<String, Object> map=new HashMap<>();
        map.put("name", stockName);
        List<Stock> stocks =  stockMapper.queryList(map);
        if (!CollectionUtils.isEmpty(stocks)) {
            Stock stock = stocks.get(0);
            stock.setStock(stock.getStock() - 1);
            stockMapper.update(stock);
        }
    }
    // 秒杀商品前判断是否有库存
    @Override
    public Integer queryStockList(String stockName) {
        Map<String, Object> map=new HashMap<>();
        map.put("name", stockName);
        List<Stock> stocks =  stockMapper.queryList(map);
        if (!CollectionUtils.isEmpty(stocks)) {
            return stocks.get(0).getStock().intValue();
        }
        return 0;
    }

}
