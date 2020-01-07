package com.blj.service.impl;

import com.blj.common.exception.ExceptionEnums;
import com.blj.common.exception.TtException;
import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import com.blj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BaiLiJun on 2020/1/7 0007
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getList() {
        Map<String, Object> map = new HashMap<>();
        List<User> list = userMapper.queryList(map);
        return list;
    }

    @Override
    public User queryById(Long id) {

        User user = userMapper.queryById(id);
        if (user == null) {
            throw new TtException(ExceptionEnums.USER_NOT_BE_FIND);
        }
        return user;
    }
}
