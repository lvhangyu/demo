package com.forezp.pojo.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class FlightDO {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    @IsKey                         //actable主键注解
    @IsAutoIncrement             //自增
    @Column(comment = "主键id")                   //对应数据库字段，不配置name会直接采用属性名作为字段名
    private Long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @Column(name = "flight_date",comment = "航班日期", type = MySqlTypeConstant.TIMESTAMP)
    private Date flightDate;
    @Column(name = "aviation_corp",comment = "航空公司")
    private String aviationCorp;
    @Column(name = "flight_code",comment = "航班编号")
    private String flightCode;
    @Column(name = "departure_addr",comment = "始发地点")
    private String departureAddr;
    @Column(name = "arrival_addr",comment = "到达地点")
    private String arrivalAddr;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "departure_time",comment = "起飞时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date departureTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "arrival_time",comment = "到达时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date arrivalTime;
    @Column(name = "flight_time",comment = "飞行时间")
    private String flightTime;
    @Column(name = "luggage",comment = "托运行李额,0无免费托运 1托运行李额20kg")
    private Integer luggage;
    @Column(name = "fuel_surcharge",comment = "燃油费",type = MySqlTypeConstant.DECIMAL,length = 65)
    private BigDecimal fuelSurcharge;
    @Column(name = "airport_tax",comment = "机建费",type = MySqlTypeConstant.DECIMAL,length = 65)
    private BigDecimal airportTax;
    @Column(name = "remaining_ticket",comment = "剩余票数")
    private Integer remainingTicket;
    @Column(name = "ticket_price",comment = "票价",type = MySqlTypeConstant.DECIMAL,length = 65)
    private BigDecimal ticketPrice;
    @Column(name = "discounted_ticket_price",comment = "特价票价",type = MySqlTypeConstant.DECIMAL,length = 65)
    private BigDecimal discountedTicketPrice;
    @Column(name = "discount",comment = "折扣力度")
    private String discount;
    @Column(name = "cabin_type",comment = "仓位类型, 0 头等舱，1 商务舱，2 经济舱")
    private Integer cabinType;
    @Column(name = "seat_count",comment = "座位数")
    private Integer seatCount;
//    @JsonFormat(shape= JsonFormat.Shape.STRING)
    @Column(name = "seat_list",comment = "座位列表", type = MySqlTypeConstant.TEXT)
    private String seatList;
    @Column(name = "ctime",comment = "创建时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date ctime = new Date();
    @Column(name = "mtime",comment = "修改时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date mtime = new Date();

    @Column(name = "boarding_time",comment = "登机时间", type = MySqlTypeConstant.TIMESTAMP)
    private Date boardingTime;
    @Column(name = "boarding_room",comment = "登机室")
    private String boardingRoom;


    @Data
    public static class Seat{
        private Long seatNumber;
        private Boolean exits;
        private Long userId;
        private String userName;

    }
}
