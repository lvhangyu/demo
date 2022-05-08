package com.forezp.pojo.vo;

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
    private String content ;
    private String images ;
    private String video ;
    private String videoCoverImg ;
    private Long creatorId ;
    private Integer likes ;
    private Integer collections ;
    private Integer comments ;
    private Integer status;
    private boolean liked;
    private boolean collected;
    private Date ctime;
    private Date mtime;
}
