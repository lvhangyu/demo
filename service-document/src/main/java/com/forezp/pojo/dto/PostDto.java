package com.forezp.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName PostDto
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 21:37
 * Version 1.0
 */
@Data
public class PostDto {
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
}
