package com.forezp.service;

import com.forezp.pojo.dao.SeatManage;

import java.util.List;

public interface SeatManageService {


    List<SeatManage> create(Integer number, Long flightId);

    List<SeatManage> list(Long flightId);

    SeatManage info(Long seatId);

    SeatManage updateStatus(Long seatId, Integer status);
}
