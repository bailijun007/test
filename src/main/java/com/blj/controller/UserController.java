package com.blj.controller;

import com.blj.common.util.ResponseResult;
import com.blj.pojo.User;
import com.blj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //查询前十条用户信息
    @GetMapping(value = "/getList")
    public ResponseEntity<List<User>> getList() {
        return ResponseEntity.ok(userService.getList());
    }

//    @GetMapping(value = "/queryById")
//    public ResponseEntity<User> queryById(@RequestParam("id") Long id) {
//        log.info("进入通过id查询用户接口，用户id为:{}", id);
//        return ResponseEntity.ok(userService.queryById(id));
//    }


//    @GetMapping(value = "/queryById")
//    public User queryById(@RequestParam("id") Long id) {
//        log.info("进入通过id查询用户接口，用户id为:{}", id);
//        return userService.queryById(id);
//    }


    @GetMapping(value = "/queryById")
    public ResponseResult<User> queryById(@RequestParam("id") Long id) {
        log.info("进入通过id查询用户接口，用户id为:{}", id);
        User user = userService.queryById(id);
        ResponseEntity<User> entity = ResponseEntity.ok(userService.queryById(id));
        ResponseResult result2 = new ResponseResult(entity);
        return result2;
    }


}
