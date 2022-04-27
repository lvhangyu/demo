package com.forezp.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ClazzVo {
    private Long id;
    @JsonFormat
    private String content;
}
