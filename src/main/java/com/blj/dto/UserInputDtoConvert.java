package com.blj.dto;

import com.blj.pojo.User;
import org.springframework.beans.BeanUtils;

/**
 * UserInputDtoConvert
 *
 * @author bailj@linkstec.com
 * @date 2020/6/23 15:00
 */
public class UserInputDtoConvert<UserInputDto> implements DTOConvert<UserInputDto, User> {

    @Override
    public User convert(UserInputDto userInputDto) {
        User user = new User();
        BeanUtils.copyProperties(userInputDto, user);
        return user;
    }
}
