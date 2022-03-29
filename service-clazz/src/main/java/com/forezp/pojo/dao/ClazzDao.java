package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import lombok.Data;

/**
 * @ClassName ClazzDto
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/29 16:28
 * Version 1.0
 */
@Data
@Table(name = "ls_ams_clazz")
public class ClazzDao {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(comment = "主键id")                   //对应数据库字段，不配置name会直接采用属性名作为字段名
    private Long id;
    @IsNotNull
    @Column(name = "content",comment = "课表内容")
    private String content;
}