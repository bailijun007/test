package com.blj.controller;

import com.blj.common.util.PageResult;
import com.blj.common.util.ResponseResult;
import com.blj.pojo.User;
import com.blj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author BaiLiJun on 2020/1/7 0007
 */
@RestController
@Slf4j
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping(value = "/queryById")
//    public User queryById(@RequestParam("id") Long id) {
//        log.info("进入通过id查询用户接口，用户id为:{}", id);
//        return userService.queryById(id);
//    }




    @GetMapping(value = "/queryById")
    public ResponseResult<User> queryById(@RequestParam("id") Long id) {
        log.info("进入通过id查询用户接口，用户id为:{}", id);
        User user = userService.queryById(id);

        return new ResponseResult(user);
    }


    //查询前十条用户信息
//    @GetMapping(value = "/getList")
//    public List<User> getList() {
//        return userService.getList();
//    }

    //查询前十条用户信息
    @GetMapping(value = "/getList")
    public ResponseResult<List<User>> getList() {
        List<User> list = userService.getList();
        return new ResponseResult(list);
    }


    @GetMapping(value = "/pageList")
    public ResponseResult<PageResult<User>> pageList(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,@RequestParam(value = "age",required = false) Integer age) {

        PageResult<User> pageResult = userService.pageList(pageNo,pageSize,age);

        ResponseResult result = new ResponseResult(pageResult);
        return result;
    }


}
