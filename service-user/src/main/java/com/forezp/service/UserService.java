package com.forezp.service;

import com.forezp.exception.MyException;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dto.UserDto;
import com.forezp.pojo.vo.UserVo;

import java.util.List;

public interface UserService {

    UserVo login(UserDto userDto) throws Exception;

    UserVo register(UserDto userDto);


    UserVo update(UserDto userDto, UserInfo userInfo);

    UserVo emailMatch(String email);

    void delete(Long id);

    UserVo updatePassword(UserDto userDto);

    List<UserVo> queryByRole(Long role);
}
