package com.blj.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author BaiLiJun  on 2019/12/26
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
