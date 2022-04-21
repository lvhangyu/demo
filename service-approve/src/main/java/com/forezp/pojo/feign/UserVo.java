package com.forezp.pojo.feign;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Long id;
    private Integer role;
    private String username;
    private Integer gender;
    private Integer age;
    private String email;
    private String mobile;
    private Date registrationTime;
    private String token;
}
