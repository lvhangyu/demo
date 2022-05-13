package com.forezp.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.SeatManageMapper;
import com.forezp.pojo.dao.FlightDO;
import com.forezp.pojo.dao.SeatManage;
import com.forezp.service.SeatManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatManageServiceImpl implements SeatManageService {

    @Autowired
    private SeatManageMapper seatManageMapper;

    @Override
    public List<SeatManage> create(Integer number, Long flightId) {
        List<SeatManage> seatManageList = new ArrayList<>();
//        for (int i = 0; i < number; i ++){
//            SeatManage seatManage = new SeatManage();
//            seatManage.setSeatNumber(flightId);
//            seatManage.setFlightId(flightId);
//            seatManage.setStatus(0);
//            seatManage.setCtime(new Date());
//            seatManage.setMtime(new Date());
//            seatManageMapper.insert(seatManage);
//            seatManageList.add(seatManage);
//        }
        return seatManageList;
    }

    @Override
    public List<SeatManage> list(Long flightId) {
        QueryWrapper<SeatManage> wrapper = new QueryWrapper<>();
        wrapper.eq("flight_id", flightId);
        wrapper.orderByAsc("seat_number");
        List<SeatManage> seatManageList = seatManageMapper.selectList(wrapper);
        return seatManageList;
    }

    @Override
    public SeatManage info(Long seatId) {
        SeatManage seatManage = seatManageMapper.selectById(seatId);
        return seatManage;
    }

    @Override
    public SeatManage updateStatus(Long seatId, Integer status) {
        SeatManage seatManage = seatManageMapper.selectById(seatId);
       // seatManage.setStatus(status);
        seatManageMapper.updateById(seatManage);
        return seatManage;
    }
}
