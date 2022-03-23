package com.forezp.controller;

import com.forezp.pojo.User;
import com.forezp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public List<User> userQuery(){
        return userService.userQuery(1);
    }
}
