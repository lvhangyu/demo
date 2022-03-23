package com.forezp.service;

import com.forezp.pojo.User;

import java.util.List;

public interface UserService {
    User saveOrUpdate(User user);
    List<User> userQuery(Integer role);
}
