package com.blj.service;

import com.blj.pojo.User;

import java.util.List;

/**
 * @author BaiLiJun on 2020/1/7 0007
 */
public interface UserService {
    List<User> getList();

    User queryById(Long id);
}