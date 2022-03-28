package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class UserDao {
    @TableId(type = IdType.AUTO)
    private Long id;
    //0管理员1普通用户
    private Integer role;
    private String name;
    //0男1女
    private Integer gender;
    private Integer age;
    private String email;
    private Integer mobile;
    private Date registrationTime;
    private String password;

}
