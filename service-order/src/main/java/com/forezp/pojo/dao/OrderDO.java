package com.forezp.pojo.dao;

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
@Table(name = "ls_order")
@TableName("ls_order")
public class OrderDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(comment = "主键id")                   //对应数据库字段，不配置name会直接采用属性名作为字段名
    private Long id;
    @Column(name = "user_id",comment = "用户id")
    private Long userId;
    @Column(name = "flight_id",comment = "航班id")
    private Long flightId;
    @Column(name = "order_code",comment = "订单编号")
    private String orderCode;
    @Column(name = "user_name",comment = "乘机人")
    private String userName;
    @Column(name = "user_mobile",comment = "联系方式")
    private Long mobile;
    @Column(name = "boarding_time",comment = "登机时间")
    private Date boardingTime;
    @Column(name = "boarding_room",comment = "登机室")
    private Date boardingRoom;
    @Column(name = "ticket_type",comment = "机票类型")
    private Integer ticketType;
    @Column(name = "ticket_price",comment = "机票价格")
    private Integer ticketPrice;
    @Column(name = "status",comment = "订单价格")
    private Integer status;
}
