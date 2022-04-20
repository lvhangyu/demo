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
 * 帖子点赞记录表
 */
@Data
@Table(name = "ls_post_like")
@TableName("ls_post_like")
public class PostLikeDao {

    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(name = "id", comment = "主键id")
    private Long id;
    @Column(name = "post_id", comment = "帖子id")
    private Long postId;
    @Column(name = "user_id", comment = "用户id")
    private Long userId;
    @Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime;
    @Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;
}
