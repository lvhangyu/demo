package com.forezp.pojo.vo;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import lombok.Data;

import java.util.Date;

@Data
public class AbsenceVo {
    private Long id;
    private Date absenceTime;
    private String majorName;
    private String reason;
    private Integer status;
    private Date ctime;
    private Date mtime;
}
