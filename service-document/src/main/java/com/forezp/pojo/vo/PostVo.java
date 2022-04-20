package com.forezp.pojo.vo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName PostVo
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 21:59
 * Version 1.0
 */
@Data
public class PostVo {
    private Long id;
    private String title ;
    private Integer type ;
    private String content ;
    private String images ;
    private Long creatorId ;
    private Integer likes ;
    private Integer collections ;
    private Integer comments ;
    private Integer status;
    private boolean liked;
    private Date ctime;
    private Date mtime;
}
