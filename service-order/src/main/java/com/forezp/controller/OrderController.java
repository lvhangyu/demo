package com.forezp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 18:40
 * Version 1.0
 */
@RestController
public class OrderController {
    @RequestMapping(value = "/query",method = RequestMethod.GET)
    public String test(){
        System.out.println("cc");
        return "cc";
    }
}
