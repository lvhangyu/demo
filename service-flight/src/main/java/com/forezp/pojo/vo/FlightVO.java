package com.forezp.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.forezp.pojo.dao.FlightDO;
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
public class FlightVO {
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
    private String flightTime;
    private Integer luggage;
    private BigDecimal fuelSurcharge;
    private BigDecimal airportTax;
    private Integer remainingTicket;
    private BigDecimal ticketPrice;
    private Integer cabinType;
    private BigDecimal discountedTicketPrice;
    private String discount;
    private Integer seatCount;
    private String seatList;
    private Date ctime;
    private Date boardingTime;
    private String boardingRoom;
    private Date mtime;
}
