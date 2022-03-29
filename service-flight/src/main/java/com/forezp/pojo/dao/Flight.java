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
 * @ClassName Flight
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 15:47
 * Version 1.0
 */
@Data
@Table(name = "ls_flight")
@TableName("ls_flight")
public class Flight {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(comment = "主键id")                   //对应数据库字段，不配置name会直接采用属性名作为字段名
    private Long id;
    @Column(name = "flight_date",comment = "航班日期")
    private Date flightDate;
    @Column(name = "aviation_corp",comment = "航空公司")
    private String aviationCorp;
    @Column(name = "flight_code",comment = "航班编号")
    private String flightCode;
    @Column(name = "departure_addr",comment = "始发地点")
    private String departureAddr;
    @Column(name = "arrival_addr",comment = "到达地点")
    private String arrivalAddr;
    @Column(name = "departure_time",comment = "起飞时间")
    private Date departureTime;
    @Column(name = "arrival_time",comment = "到达时间")
    private Date arrivalTime;
    @Column(name = "flight_time",comment = "飞行时间")
    private Date flightTime;
    @Column(name = "luggage",comment = "托运行李额")
    private Integer luggage;
    @Column(name = "fuel_surcharge",comment = "燃油费")
    private Integer fuelSurcharge;
    @Column(name = "airport_tax",comment = "机建费")
    private Integer airportTax;
    @Column(name = "remaining_ticket",comment = "剩余票数")
    private Integer remainingTicket;
    @Column(name = "normal_fare",comment = "普通票价")
    private Integer normalFare;
    @Column(name = "child_fare",comment = "儿童票价")
    private Integer childFare;
    @Column(name = "ctime",comment = "创建时间")
    private Date ctime;
    @Column(name = "mtime",comment = "修改时间")
    private Date mtime;
}
