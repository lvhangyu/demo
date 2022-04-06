package com.forezp.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Order
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 17:35
 * Version 1.0
 */
@Data
public class OrderDTO {
    private Long id;
    private Long userId;
    private Long flightId;
    private String orderCode;
    private String userName;
    private String userNumber;
    private String mobile;
    private Date boardingTime;
    private String boardingRoom;
    private Integer ticketType;
    private BigDecimal ticketPrice;
}
