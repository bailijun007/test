package com.blj.mapper;

import com.blj.pojo.Order;
import com.blj.pojo.Stock;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/27
 */

public interface StockMapper {

    List<Stock> queryList(Map<String,Object> map);

    Stock queryOne(Map<String,Object> map);

    void update(Stock stock);
}
