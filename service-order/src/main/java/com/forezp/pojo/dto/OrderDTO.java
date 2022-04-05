package com.forezp.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

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
    private Long mobile;
    private Date boardingTime;
    private Date boardingRoom;
    private Integer ticketType;
    private Integer ticketPrice;
    private Integer status;
}
