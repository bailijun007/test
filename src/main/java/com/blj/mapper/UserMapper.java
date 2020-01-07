package com.blj.mapper;

import com.blj.pojo.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun  on 2019/12/30
 */
public interface UserMapper extends Mapper<User> {


    List<User> queryList(Map<String,Object> map);

    User queryById(@Param("id") Long id);
}
