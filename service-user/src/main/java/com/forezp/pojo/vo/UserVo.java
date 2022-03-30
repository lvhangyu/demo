package com.forezp.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Long id;
    private Integer role;
    private String name;
    private Integer gender;
    private Integer age;
    private String email;
    private String mobile;
    private Date registrationTime;
    private String token;
}
