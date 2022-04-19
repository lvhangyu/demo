package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ChangePasswordDao
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/29 16:43
 * Version 1.0
 */
@Data
@Table(name = "ls_absence")
@TableName("ls_absence")
public class ChangePasswordDao {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(comment = "主键id")                   //对应数据库字段，不配置name会直接采用属性名作为字段名
    private Long id;
    @Column(name = "email",comment = "邮箱")
    private String email;
    @Column(name = "new_pwd",comment = "新密码")
    private String newPwd;
    @Column(name = "status",comment = "状态 0未修改，1已修改")
    private Integer status;
    @Column(name = "ctime",comment = "创建时间")
    private Date ctime;
    @Column(name = "mtime",comment = "修改时间")
    private Date mtime;
}
