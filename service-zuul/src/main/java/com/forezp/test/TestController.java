package com.forezp.test;

import com.forezp.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    public void test(){
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }
}
