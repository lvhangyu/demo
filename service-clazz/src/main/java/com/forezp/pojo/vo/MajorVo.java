package com.forezp.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MajorVo {
    private Long id;
    private String number;
    private String name;
    private Date ctime;
    private Date mtime;
}
