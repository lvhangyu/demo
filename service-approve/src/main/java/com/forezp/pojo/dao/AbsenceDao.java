package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Flight
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 15:47
 * Version 1.0
 */
@Data
@Table(name = "ls_ams_absence")
public class AbsenceDao {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(comment = "主键id")                   //对应数据库字段，不配置name会直接采用属性名作为字段名
    private Long id;
    @Column(name = "absence_time",comment = "请假日期")
    private Date absenceTime;
    @Column(name = "reason",comment = "请假理由")
    private String reason;
    @Column(name = "status",comment = "0未同意 1已同意")
    private Integer status;
    @Column(name = "ctime",comment = "创建时间")
    private Date ctime;
    @Column(name = "mtime",comment = "修改时间")
    private Date mtime;
}
