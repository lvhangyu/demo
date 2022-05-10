package com.forezp.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import lombok.Data;

import java.util.Date;
@Data
public class AbsenceDto {
    private Long id;
    private Date absenceTime;
    private String reason;
    private String absenceClazz;
    private Long userId;
    private String userNumber;
    private String userName;
    private Integer status;
}
