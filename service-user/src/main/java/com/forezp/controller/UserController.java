package com.forezp.controller;

import com.alibaba.fastjson.JSONObject;
import com.forezp.mvc.ResultModel;
import com.forezp.pojo.User;
import com.forezp.service.SchedualServiceHi;
import com.forezp.service.UserService;
import com.forezp.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController()
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    SchedualServiceHi schedualServiceHi;


    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam String name){
        String userInfo = request.getHeader("userInfo");
        log.info("当前用户信息:{}",userInfo);
        return schedualServiceHi.sayHiFromClientOne(name);
    }

    @RequestMapping("/login")
    public ResultModel login(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = userService.login();
        //将token存入Http的header中
        response.setHeader("Authorization", (String) map.get("token"));
        String token = JwtUtils.createJWTToken(JSONObject.toJSONString(map));
        map.put("token",token);
        return new ResultModel<>().setCode(HttpStatus.OK.value()).setData(JSONObject.toJSONString(map)).setMsg("success");
    }
}
