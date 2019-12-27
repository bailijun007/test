package com.blj.service;

/**
 * @author BaiLiJun  on 2019/12/27
 */
public interface StockService {
    // 秒杀商品后减少库存
   public void decrByStock(String stockName);

    // 秒杀商品前判断是否有库存
    public Integer queryStockList(String stockName);
}
