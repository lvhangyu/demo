package com.forezp.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MajorClazzVo {
    private Long id;
    private String number;
    private String name;
    private String content;
    private Date ctime;
    private Date mtime;
}
