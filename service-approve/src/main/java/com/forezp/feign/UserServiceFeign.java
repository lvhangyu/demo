package com.forezp.feign;

import com.forezp.feign.fallback.UserServiceHystric;
import com.forezp.pojo.feign.UserDto;
import com.forezp.pojo.feign.UserVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-user",fallback = UserServiceHystric.class)
public interface UserServiceFeign {
    @RequestMapping(value = "/emailMatch", method = RequestMethod.GET)
    UserVo getEmailMatch(@RequestParam(value = "email") String email);

    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    String updatePassword(@RequestBody UserDto userDto);
}
