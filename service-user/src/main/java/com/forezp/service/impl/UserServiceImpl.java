package com.forezp.service.impl;


import com.forezp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Map<String, Object> login() {
        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("username","adb");
        userInfo.put("uid","123");
        userInfo.put("role","admin");
        return userInfo;
    }
}
