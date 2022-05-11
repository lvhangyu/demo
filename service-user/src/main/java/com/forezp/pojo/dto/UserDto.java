package com.forezp.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private String username;
    private String password;
    private Integer role;
    private Integer gender;
    private Integer age;
    private String email;
    private String mobile;
}
