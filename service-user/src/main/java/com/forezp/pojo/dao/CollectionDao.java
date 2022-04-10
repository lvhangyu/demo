package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * 收藏
 */
@Data
@Table(name = "ls_collection")
@TableName("ls_collection")
public class CollectionDao {
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(name = "id", comment = "主键id")
    private Long id;
    @Column(name = "user_id",comment = "角色id")
    private Long userId;
    @Column(name = "note_id",comment = "帖子/资源id")
    private Integer noteId;
    @Column(name = "type",comment = "0帖子1资源")
    private Integer type;
    @Column(name = "ctime", comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime = new Date();
    @Column(name = "mtime", comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime;

}
