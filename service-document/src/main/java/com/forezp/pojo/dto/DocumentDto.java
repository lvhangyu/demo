package com.forezp.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentDto {

    private Long id;
    private String title;
    private String content;
    private String images;
}
