package com.forezp.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName Flight
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 15:47
 * Version 1.0
 */
@Data
@Accessors(chain = true)
public class FlightDTO {
    private Long id;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date flightDate;
    private String aviationCorp;
    private String flightCode;
    private String departureAddr;
    private String arrivalAddr;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date departureTime;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date arrivalTime;
    private Integer luggage;
    private BigDecimal fuelSurcharge;
    private BigDecimal airportTax;
    private Integer remainingTicket;
    private BigDecimal ticketPrice;
    private Integer cabinType;
    private BigDecimal discountedTicketPrice;
    private String discount;
    private Integer seatCount;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date ctime;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date mtime;
}
