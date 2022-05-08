package com.forezp.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentVo {

    private Long id;
    private String title;
    private String content;
    private String images;
    private Integer collections;
    private boolean collected;
    private Date ctime = new Date();
    private Date mtime;
}
