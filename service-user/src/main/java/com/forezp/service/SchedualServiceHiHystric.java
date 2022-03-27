package com.forezp.service;

import com.forezp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fangzhipeng on 2017/4/6.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Autowired
    private UserMapper userMapper;
    @Override
    public String sayHiFromClientOne(String name) {
        userMapper.selectList(null);
        return "sorry "+name;
    }
}
