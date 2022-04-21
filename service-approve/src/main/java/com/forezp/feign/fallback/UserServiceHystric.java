package com.forezp.feign.fallback;

import com.forezp.feign.UserServiceFeign;
import com.forezp.pojo.feign.UserDto;
import com.forezp.pojo.feign.UserVo;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystric implements UserServiceFeign {
    @Override
    public UserVo getEmailMatch(String email) {
        return null;
    }

    @Override
    public String update(UserDto userDto) {
        return "update fail";
    }
}
