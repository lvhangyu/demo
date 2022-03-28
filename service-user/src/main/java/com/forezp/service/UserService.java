package com.forezp.service;

import com.forezp.exception.MyException;
import com.forezp.pojo.dto.UserDto;
import com.forezp.pojo.vo.UserVo;

public interface UserService {

    UserVo login(UserDto userDto) throws MyException;
}
