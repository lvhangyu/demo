package com.forezp.service.impl;


import cn.hutool.core.bean.BeanUtil;
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
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVo login(UserDto userDto) throws Exception {
        QueryWrapper<UserDao> wrapperUser = new QueryWrapper<>();
        wrapperUser.eq("number", userDto.getNumber());
        wrapperUser.eq("password", DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        //throw new MyException(HttpStatus.UNAUTHORIZED.value(), "Incorrect account password");
        UserDao userDao = userMapper.selectOne(wrapperUser);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }

    @Override
    public UserVo register(UserDto userDto) {
        UserDao userDao = new UserDao();
        BeanUtils.copyProperties(userDto, userDao);
        userDao.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        userDao.setRegistrationTime(new Date());
        userDao.setNumber(userDto.getNumber());
        userDao.setCtime(new Date());
        userDao.setMtime(new Date());
        userDao.setStatus(0);
        int r = userMapper.insert(userDao);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }

    @Override
    public UserVo update(UserDto userDto, UserInfo userInfo) {
        UserDao userDao = userMapper.selectById(userDto.getId());
//        userDao.setRole(0);
        userDao.setUsername(userDto.getUsername());
        userDao.setGender(userDto.getGender());
        userDao.setAge(userDto.getAge());
        userDao.setEmail(userDto.getEmail());
        userDao.setMobile(userDto.getMobile());
        userDao.setStatus(userDto.getStatus());
//        userDao.setRegistrationTime(new Date());
//        userDao.setPassword("");
//        userDao.setCtime(new Date());
        userDao.setMtime(new Date());
        userMapper.updateById(userDao);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }

    @Override
    public UserVo emailMatch(String email) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", email);
        UserDao userDao = userMapper.selectOne(queryWrapper);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public UserVo updatePassword(UserDto userDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", userDto.getEmail());
        UserDao userDao = userMapper.selectOne(queryWrapper);
        userDao.setPassword(DigestUtils.md5DigestAsHex(userDto.getPassword().getBytes()));
        System.out.println(userDao);
        userMapper.updateById(userDao);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDao, userVo);
        return userVo;
    }

    @Override
    public List<UserVo> queryByRole(Long role) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("role", role);
        List<UserDao> userDaoList = userMapper.selectList(queryWrapper);
        List<UserVo> userVoList = BeanUtil.copyToList(userDaoList, UserVo.class, null);
        return userVoList;
    }
}
