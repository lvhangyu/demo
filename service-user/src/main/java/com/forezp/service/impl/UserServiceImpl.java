package com.forezp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.exception.MyException;
import com.forezp.mapper.UserMapper;
import com.forezp.pojo.dao.UserDao;
import com.forezp.pojo.dto.UserDto;
import com.forezp.pojo.vo.UserVo;
import com.forezp.service.UserService;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVo login(UserDto userDto) throws MyException {
        QueryWrapper<UserDao> wrapperUser = new QueryWrapper<>();
        wrapperUser.eq("username", userDto.getUsername());
        wrapperUser.eq("passwrod", MD5Encoder.encode(userDto.getPassword().getBytes()));
        //throw new MyException(HttpStatus.UNAUTHORIZED.value(), "Incorrect account password");
        UserDao userDao = userMapper.selectOne(wrapperUser);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }
}
