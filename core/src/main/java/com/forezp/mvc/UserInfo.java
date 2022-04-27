package com.forezp.mvc;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {

    private Long id;
    private Integer role;
    private String username;
    private String number;
    private String majorId;
    private Integer gender;
    private Integer age;
    private String email;
    private String mobile;
    private Date registrationTime;
    private String token;
}
