package com.blj.dto;

/**
 * DTOConvert
 *
 * @author bailj@linkstec.com
 * @date 2020/6/23 14:51
 */
public interface DTOConvert<T,R> {

    /**
     * @param  t 转换前对象类型
     * @return R 转换后对象类型
     */
    R convert(T t);
}
