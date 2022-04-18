package com.forezp.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo {

    private Long id;
    private Long postId;
    private Long parentId;
    private Long userId;
    private String content;
    private Integer likes;
    private List<CommentVo> commentVoList;
    private Date ctime;
    private Date mtime;
}
