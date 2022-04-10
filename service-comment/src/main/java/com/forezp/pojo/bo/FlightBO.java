package com.forezp.pojo.bo;

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
public class FlightBO {
    private Long id;
    private Date flightDate;
    private String aviationCorp;
    private String flightCode;
    private String departureAddr;
    private String arrivalAddr;
    private Date departureTime;
    private Date arrivalTime;
    private Date flightTime;
    private Integer luggage;
    private Integer fuelSurcharge;
    private Integer airportTax;
    private Integer remainingTicket;
    private Integer normalFare;
    private Integer childFare;
    private Date ctime;
    private Date mtime;
}
