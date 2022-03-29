package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

@Data
@Table(name = "ls_comment")
@TableName("ls_comment")
public class CommentDao {
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(name = "id", comment = "主键id")
    private Long id;
    @Column(name = "post_id",comment = "帖子id")
    private Long postId;
    @Column(name = "parent_id",comment = "父级id")
    private Long parentId;
    @Column(name = "creator_id",comment = "创建人")
    private Long creatorId;
    @Column(name = "content",comment = "内容")
    private String content;
    @Column(name = "likes",comment = "点赞数")
    private Integer likes;
    @Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime = new Date();
    @Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;

}
