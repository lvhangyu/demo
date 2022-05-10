package com.forezp.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Long id;
    private Integer role;
    private String username;
    private String number;
    private String majorId;
    private Integer gender;
    private Integer age;
    private String email;
    private String mobile;
    private Integer status;
    private Date registrationTime;
    private String token;
}
