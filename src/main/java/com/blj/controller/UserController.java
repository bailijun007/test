package com.blj.controller;

import com.blj.common.exception.ExceptionEnums;
import com.blj.common.exception.TtException;
import com.blj.common.util.PageResult;
import com.blj.common.util.ResponseResult;
import com.blj.dto.UserInputDto;
import com.blj.dto.UserInputDtoConvert;
import com.blj.pojo.User;
import com.blj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    // 查询前十条用户信息
//    @GetMapping(value = "/getList")
//    public List<User> getList() {
//        return userService.getList();
//    }

    // 查询前十条用户信息
    @GetMapping(value = "/getList")
    public ResponseResult<List<User>> getList() {
        List<User> list = userService.getList();
        return new ResponseResult(list);
    }


    @GetMapping(value = "/pageList")
    public ResponseResult<PageResult<User>> pageList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "age", required = false) Integer age) {

        PageResult<User> pageResult = userService.pageList(pageNo, pageSize, age);


        ResponseResult result = new ResponseResult(pageResult);
        return result;
    }


    /**
     * 新增用户接口
     * controller应该要加上@Valid ,否则不会验证!
     *
     * @param inputDto
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/addUser")
    public User addUser(@Valid UserInputDto inputDto, BindingResult bindingResult) {
        this.checkDtoParam(bindingResult);
        User user = new UserInputDtoConvert().convert(inputDto);
        User saveUser = userService.saveUser(user);
        return saveUser;
    }


    private void checkDtoParam(BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError oe : allErrors) {
                String key = null;
                String msg = null;
                // 字段错误
                if (oe instanceof FieldError) {
                    FieldError fe = (FieldError) oe;
                    // 获取错误验证字段名
                    key = fe.getField();
                } else {
                    // 非字段错误 获取验证对象名称
                    key = oe.getObjectName();
                }
                msg =oe.getDefaultMessage();
                log.error("校验字段:{}发生错误，错误原因:{}", key,msg);
            }

            throw new TtException(ExceptionEnums.PARAM_ERROR);
        }
    }

}
