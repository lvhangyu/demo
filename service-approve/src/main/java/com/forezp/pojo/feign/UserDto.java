package com.forezp.pojo.feign;

import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String userNum;
    private Integer role;
    private Integer gender;
    private Integer age;
    private String email;
    private String mobile;
}
