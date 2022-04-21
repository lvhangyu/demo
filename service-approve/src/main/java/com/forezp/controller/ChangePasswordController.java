package com.forezp.controller;

import com.forezp.exception.MyException;
import com.forezp.feign.UserServiceFeign;
import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.feign.UserDto;
import com.forezp.pojo.feign.UserVo;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;

@RestController
public class ChangePasswordController {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Autowired
    JavaMailSender javaMailSender;

    @PostMapping("/changePassword")
    public void changePassword(
            @RequestParam(required = false, value = "new_pwd") String newPwd,
            @RequestParam(required = false, value = "email") String email,
            HttpRequest httpRequest, HttpResponse httpResponse
    ){
        UserVo userVo = userServiceFeign.getEmailMatch(email);
        if (null != userVo){
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userVo,userDto);
            byte[] decodeBase64 = Base64.decodeBase64(newPwd);
            String newPwdDecoded = new String(decodeBase64);
            userDto.setPassword(newPwdDecoded);
            userServiceFeign.update(userDto);

            //发送邮件
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject("重置密码通知");
            message.setFrom("sjenterrement@qq.com");
            message.setTo(email);
            message.setSentDate(new Date());
            message.setText("您的密码已重置");
            javaMailSender.send(message);
        }else {
            new MyException(404, "error email");
        }
    }
}
