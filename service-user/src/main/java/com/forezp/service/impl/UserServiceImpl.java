package com.forezp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.exception.MyException;
import com.forezp.mapper.UserMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.UserDao;
import com.forezp.pojo.dto.UserDto;
import com.forezp.pojo.vo.UserVo;
import com.forezp.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVo login(UserDto userDto) throws MyException {
        QueryWrapper<UserDao> wrapperUser = new QueryWrapper<>();
        wrapperUser.eq("username", userDto.getUsername());
        wrapperUser.eq("password", DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        //throw new MyException(HttpStatus.UNAUTHORIZED.value(), "Incorrect account password");
        UserDao userDao = userMapper.selectOne(wrapperUser);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }

    @Override
    public UserVo register(UserDto userDto) throws MyException {
        Map map = new HashMap<>();
        map.put("username",userDto.getUsername());
        List<UserDao> userDaoList = userMapper.selectByMap(map);
        if(null != userDaoList && userDaoList.size() > 0){
            throw new MyException(400, "用户名已存在");
        }
        UserDao userDao = new UserDao();
        BeanUtils.copyProperties(userDto, userDao);
        userDao.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        userDao.setRegistrationTime(new Date());
        userDao.setCtime(new Date());
        userDao.setMtime(new Date());
        int r = userMapper.insert(userDao);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }

    @Override
    public UserVo update(UserDto userDto, UserInfo userInfo) {
        UserDao userDao = userMapper.selectById(userInfo.getId());
//        userDao.setRole(0);
        userDao.setUsername(userDto.getUsername());
        userDao.setGender(userDto.getGender());
        userDao.setAge(userDto.getAge());
        userDao.setEmail(userDto.getEmail());
        userDao.setMobile(userDto.getMobile());
//        userDao.setRegistrationTime(new Date());
//        userDao.setPassword("");
//        userDao.setCtime(new Date());
        userDao.setMtime(new Date());
        userMapper.updateById(userDao);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }
}
