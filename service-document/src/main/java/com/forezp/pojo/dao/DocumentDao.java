package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * 资源
 */
@Data
@Table(name = "ls_document")
@TableName("ls_document")
public class DocumentDao {
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(name = "id", comment = "主键id")
    private Long id;
    @Column(name = "title",comment = "标题")
    private String title;
    @Column(name = "content",comment = "内容")
    private String content;
    @Column(name = "images",comment = "图片")
    private String images;
    @Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime;
    @Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;

}
