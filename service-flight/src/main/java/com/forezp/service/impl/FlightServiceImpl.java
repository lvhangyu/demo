package com.forezp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.FlightMapper;
import com.forezp.pojo.dao.FlightDO;
import com.forezp.pojo.dto.FlightDTO;
import com.forezp.pojo.vo.FlightVO;
import com.forezp.service.FlightService;
import com.forezp.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        flightDO.setCtime(new Date());
        flightDO.setMtime(new Date());
        List<FlightDO.Seat> seatList = new ArrayList<>();
        if(null != flightDTO.getSeatCount()){
            for (int i = 1; i <= flightDTO.getSeatCount(); i ++){
                FlightDO.Seat seat = new FlightDO.Seat();
                seat.setSeatNumber(String.valueOf(i));
                seat.setExits(true);
                seatList.add(seat);
            }
            flightDO.setSeatList(JSONObject.toJSONString(seatList));
        }
        int result = flightMapper.insert(flightDO);
        FlightVO flightVO = new FlightVO();
        BeanUtils.copyProperties(flightDO, flightVO);
        return flightVO;
    }

    @Override
    public void delete(Long id) {
        flightMapper.deleteById(id);
    }

    @Override
    public FlightVO update(FlightDTO flightDTO) {
        FlightDO flightDO = flightMapper.selectById(flightDTO.getId());
        BeanUtils.copyProperties(flightDTO, flightDO);
        flightDO.setMtime(new Date());
        flightMapper.updateById(flightDO);
        FlightVO flightVO = new FlightVO();
        BeanUtils.copyProperties(flightDO, flightVO);
        return flightVO;
    }

    @Override
    public List<FlightVO> query() {
        List<FlightDO> flightDOList = flightMapper.selectList(null);
        List<FlightVO> flightVOList = new ArrayList<>(16);
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.ignoreCase();
        flightVOList = BeanUtil.copyToList(flightDOList, FlightVO.class, copyOptions);
        return flightVOList;
    }

    @Override
    public FlightVO info(Long id) {
        FlightDO flightDO = flightMapper.selectById(id);
        FlightVO flightVO = new FlightVO();
        BeanUtils.copyProperties(flightDO, flightVO);
        return flightVO;
    }

    @Override
    public List<FlightVO> serach(Date flightDate, String aviationCorp, String departureAddr, String arrivalAddr, Date departureTime, Date arrivalTime, BigDecimal ticketPrice, Integer cabinType) {
        QueryWrapper<FlightDO> wrapper = new QueryWrapper<>();
        if (null != flightDate){
            wrapper.eq("flight_date", flightDate);
        }
        if (StringUtils.isNotEmpty(aviationCorp)){
            wrapper.like("aviation_corp", aviationCorp);
        }
        if (StringUtils.isNotEmpty(departureAddr)){
            wrapper.like("departure_addr", departureAddr);
        }
        if (StringUtils.isNotEmpty(arrivalAddr)){
            wrapper.like("arrival_addr", arrivalAddr);
        }
//        if (StringUtils.isNotEmpty(arrivalAddr)){
//            wrapper.like("arrival_addr", arrivalAddr);
//        }
        if (null != ticketPrice){
            wrapper.le("ticket_price", ticketPrice);
        }
        if (null != cabinType){
            wrapper.eq("cabin_type", cabinType);
        }
        List<FlightDO> flightDOList = flightMapper.selectList(wrapper);
        List<FlightVO> flightVOList = new ArrayList<>(16);
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.ignoreCase();
        flightVOList = BeanUtil.copyToList(flightDOList, FlightVO.class, copyOptions);
        return flightVOList;
    }

    @Override
    public List<FlightVO> discountedTicket() {
        QueryWrapper<FlightDO> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("discounted_ticket_price");
        wrapper.isNotNull("discount");
        wrapper.orderByDesc("flight_date");
        List<FlightDO> flightDOList = flightMapper.selectList(wrapper);
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.ignoreCase();
        List<FlightVO> flightVOList = BeanUtil.copyToList(flightDOList, FlightVO.class, copyOptions);
        return flightVOList;
    }
}
