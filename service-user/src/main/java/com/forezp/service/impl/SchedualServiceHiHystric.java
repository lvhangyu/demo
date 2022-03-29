package com.forezp.service.impl;

import com.forezp.mapper.UserMapper;
import com.forezp.service.SchedualServiceHi;
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
