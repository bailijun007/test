package com.blj.service.impl;

import com.blj.common.exception.ExceptionEnums;
import com.blj.common.exception.TtException;
import com.blj.common.util.PageResult;
import com.blj.mapper.UserMapper;
import com.blj.pojo.User;
import com.blj.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        map.put("limit", 10);
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

    @Override
    public PageResult<User> pageList(Integer pageNo, Integer pageSize, Integer age) {
        PageResult<User> pageResult = new PageResult<>();
        PageHelper.startPage(pageNo, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("age", age);
        List<User> users = userMapper.queryList(map);
        PageInfo<User> info = new PageInfo<>(users);
        pageResult.setList(users)
                .setPageNo(info.getPageNum())
                .setPageCount(info.getPages())
                .setRowTotal(info.getTotal());
        return pageResult;
    }

    @Override
    public User saveUser(User user) {
        user.setCreateDate(LocalDate.now())
                .setCreateTime(LocalDateTime.now());
        userMapper.save(user);
        User userDto = userMapper.queryByName(user.getName());
        return userDto;
    }

    @Override
    public void deleteById(Long id) {
        //判断该id是否存在
        User user = userMapper.queryById(id);
        if (null == user) {
            throw new TtException(ExceptionEnums.USER_NOT_BE_FIND);
        }
        userMapper.deleteById(id);
    }


    /**
     * 测试@Transactional注解是否能回滚
     *
     */
    @Override
    public void testTransactional(){
        User user = userMapper.queryById(3L);
        LocalDate createDate = user.getCreateDate();
        if (createDate!=null){
            user.setCreateDate(LocalDate.now());
            userMapper.updateById(user);
        }

        String password = user.getPassword();

       try {
           // 故意报错
           String substring = password.substring(0, 1);
           System.out.println("substring = " + substring);
       }catch (Exception e){
           throw new TtException(ExceptionEnums.DEFAULT_ERROR);
       }


    }

}
