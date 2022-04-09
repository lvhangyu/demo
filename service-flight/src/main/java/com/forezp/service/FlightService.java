package com.forezp.service;

import com.forezp.mvc.ResultModel;
import com.forezp.pojo.dto.FlightDTO;
import com.forezp.pojo.vo.FlightVO;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @ClassName FlightService
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:36
 * Version 1.0
 */
public interface FlightService {
    FlightVO save(FlightDTO flightDTO) throws ParseException;
    void delete(Long id);
    FlightVO update(FlightDTO flightDTO);
    List<FlightVO> query();
    FlightVO info(Long id);
    List<FlightVO> serach(Date flightDate, String aviationCorp, String departureAddr, String arrivalAddr, Date departureTime, Date arrivalTime, BigDecimal ticketPrice, Integer cabinType);
}
