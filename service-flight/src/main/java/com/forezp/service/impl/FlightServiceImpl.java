package com.forezp.service.impl;

import com.forezp.mapper.FlightMapper;
import com.forezp.pojo.dao.FlightDO;
import com.forezp.pojo.dto.FlightDTO;
import com.forezp.pojo.vo.FlightVO;
import com.forezp.service.FlightService;
import com.forezp.util.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * @ClassName FlightServiceImpl
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:36
 * Version 1.0
 */
@Service
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightMapper flightMapper;
    @Override
    public FlightVO save(FlightDTO flightDTO) throws ParseException {
        FlightDO flightDO = new FlightDO();
        BeanUtils.copyProperties(flightDTO, flightDO);
        flightDO.setFlightTime(DateUtil.getDurationByDate(flightDO.getArrivalTime(),flightDO.getDepartureTime(),DateUtil.dateFormat[1]));
        int result = flightMapper.insert(flightDO);
        FlightVO flightVO = new FlightVO();
        BeanUtils.copyProperties(flightDO, flightVO);
        return flightVO;
    }
}
