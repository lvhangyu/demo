package com.forezp.pojo.dto;


import lombok.Data;

@Data
public class CommentDto {

    private Long postId;
    private Long parentId;
    private String content;
}
