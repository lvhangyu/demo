package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 17:35
 * Version 1.0
 */
@Data
@Table(name = "ls_ams_major")
public class MajorDao {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(comment = "主键id")                   //对应数据库字段，不配置name会直接采用属性名作为字段名
    private Long id;
    @IsNotNull
    @Column(name = "number",comment = "专业编号")
    private String number;
    @IsNotNull
    @Column(name = "name",comment = "专业名称")
    private String name;
    @Column(name = "clazz_id",comment = "课表id")
    private Long clazzId;
    @Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime = new Date();
    @Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;
}
