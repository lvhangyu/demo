package com.forezp.service;

import com.forezp.exception.MyException;
import com.forezp.pojo.dao.SeatManage;

import java.util.List;

public interface SeatManageService {


    List<SeatManage> create(Integer number, Long flightId);

    List<SeatManage> list(Long flightId);

    SeatManage info(Long seatId);

    SeatManage updateStatus(Long seatId, Integer status);

    SeatManage set(Long flightId, Long seatNumber, String mobile, String username, String userNumber) throws MyException;

    SeatManage cancel(Long flightId, Long seatNumber);
}
