package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "ls_user")
public class UserDao {
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(name = "id", comment = "主键id")
    private Long id;
    @Column(name = "role",comment = "角色id")
    private Integer role;
    @Column(name = "username",comment = "用户名字")
    private String username;
    @Column(name = "gender",comment = "性别")
    private Integer gender;
    @Column(name = "age",comment = "年龄")
    private Integer age;
    @Column(name = "email",comment = "邮箱")
    private String email;
    @Column(name = "mobile",comment = "手机号")
    private Integer mobile;
    @Column(name = "registrationTime",comment = "注册时间")
    private Date registrationTime;
    @Column(name = "password",comment = "密码")
    private String password;
    @Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime = new Date();
    @Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;

}
