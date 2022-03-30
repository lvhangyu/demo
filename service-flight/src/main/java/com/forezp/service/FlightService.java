package com.forezp.service;

import com.forezp.pojo.dto.FlightDTO;
import com.forezp.pojo.vo.FlightVO;

import java.text.ParseException;

/**
 * @ClassName FlightService
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:36
 * Version 1.0
 */
public interface FlightService {
    FlightVO save(FlightDTO flightDTO) throws ParseException;
}
