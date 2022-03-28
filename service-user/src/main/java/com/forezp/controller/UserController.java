package com.forezp.controller;

import com.alibaba.fastjson.JSONObject;
import com.forezp.exception.MyException;
import com.forezp.mvc.ResultModel;
import com.forezp.pojo.dto.UserDto;
import com.forezp.pojo.vo.UserVo;
import com.forezp.service.SchedualServiceHi;
import com.forezp.service.UserService;
import com.forezp.util.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ResultModel<UserVo> login(
            @RequestBody UserDto userDto,
            HttpServletRequest request, HttpServletResponse response) throws MyException {
        UserVo userVo = null;
        try {
            userVo = userService.login(userDto);
        }catch (Exception e){
            throw new MyException(HttpStatus.UNAUTHORIZED.value(), "Incorrect account password");
        }
        String token = JwtUtils.createJWTToken(JSONObject.toJSONString(userVo));
        userVo.setToken(token);
        //将token存入Http的header中
        response.setHeader("Authorization", token);
        return new ResultModel<UserVo>().setCode(HttpStatus.OK.value()).setData(userVo).setMsg("success");
    }
}
