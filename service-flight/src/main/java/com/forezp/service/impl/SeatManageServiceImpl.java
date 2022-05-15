package com.forezp.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.exception.MyException;
import com.forezp.mapper.FlightMapper;
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


    @Autowired
    private FlightMapper flightMapper;
    @Override
    public synchronized SeatManage set(Long flightId, Long seatNumber, Long userId, String username, String userNumber) throws MyException {
        FlightDO flightDO = flightMapper.selectById(flightId);
        JSONArray jsonArray = JSONObject.parseArray(flightDO.getSeatList());
        for (Object object : jsonArray){
            JSONObject jsonObject = (JSONObject) object;
            if(seatNumber.equals(jsonObject.getLong("seatNumber"))){
                if(!jsonObject.getBoolean("exits")){
                    throw new MyException(400, "航班座位已被占!");
                }
                jsonObject.put("exits",Boolean.valueOf("false"));
                jsonObject.put("userId",userId);
                jsonObject.put("userName", username);
                jsonObject.put("userNumber", userNumber);
            }
        }
        flightDO.setSeatCount(flightDO.getSeatCount() - 1);
        flightDO.setSeatList(jsonArray.toJSONString());
        flightMapper.updateById(flightDO);
        return null;
    }


    @Override
    public synchronized SeatManage cancel(Long flightId, Long seatNumber) {
        FlightDO flightDO = flightMapper.selectById(flightId);
        JSONArray jsonArray = JSONObject.parseArray(flightDO.getSeatList());
        for (Object object : jsonArray){
            JSONObject jsonObject = (JSONObject) object;
            if(seatNumber.equals(jsonObject.getLong("seatNumber"))){
                jsonObject.put("exits",Boolean.valueOf("true"));
                jsonObject.remove("userId");
                jsonObject.remove("userName");
                jsonObject.remove("userNumber");
            }
        }
        flightDO.setSeatCount(flightDO.getSeatCount() + 1);
        flightDO.setSeatList(jsonArray.toJSONString());
        flightMapper.updateById(flightDO);
        return null;
    }
}
