package com.forezp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
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
        FlightDO flightDO = new FlightDO();
        BeanUtils.copyProperties(flightDTO, flightDO);
        flightDO.setMtime(new Date());
        flightMapper.updateById(flightDO);
        FlightDO flightDONew = flightMapper.selectById(flightDO.getId());
        FlightVO flightVO = new FlightVO();
        BeanUtils.copyProperties(flightDONew, flightVO);
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
}
