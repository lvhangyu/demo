package com.forezp.controller;

import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.UserInfo;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangePasswordController {

    @PostMapping("/changePassword")
    public void changePassword(
            @RequestParam(required = false, value = "newPwd") String newPwd,
            @RequestParam(required = false, value = "email") String email,
            HttpRequest httpRequest, HttpResponse httpResponse
    ){

    }
}
