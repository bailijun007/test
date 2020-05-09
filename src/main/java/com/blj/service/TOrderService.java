package com.blj.service;

import com.blj.pojo.TOrder;
import java.util.List;

/**
 * (TOrder)表服务接口
 *
 * @author makejava
 * @since 2020-05-09 10:28:20
 */
public interface TOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrder queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    TOrder insert(TOrder tOrder);

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    TOrder update(TOrder tOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}