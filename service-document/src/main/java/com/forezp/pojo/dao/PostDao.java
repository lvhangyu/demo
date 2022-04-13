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
 * 帖子
 */
@Data
@Table(name = "ls_post")
@TableName("ls_post")
public class PostDao {
    public static Integer NOT_APPROVE = 0;
    public static Integer PASSED = 1;
    public static Integer NOT_PASS = 2;

    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(name = "id", comment = "主键id")
    private Long id;
    @Column(name = "title",comment = "标题")
    private String title ;
    @Column(name = "type",comment = "类型")
    private Integer type ;
    @Column(name = "content",comment = "内容")
    private String content ;
    @Column(name = "images",comment = "图片")
    private String images ;
    @Column(name = "creator_id",comment = "创建人")
    private Long creatorId ;
    @Column(name = "likes",comment = "点赞数")
    private Integer likes ;
    @Column(name = "conllections",comment = "收藏数")
    private Integer conllections ;
    @Column(name = "comments",comment = "评论数")
    private Integer comments ;
    @Column(name = "status",comment = "帖子状态，0待审核，1审核通过，2审核未通过")
    private Integer status;
    @Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime;
    @Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;

}
