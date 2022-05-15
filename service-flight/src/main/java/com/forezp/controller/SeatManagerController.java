package com.forezp.controller;

import com.forezp.exception.MyException;
import com.forezp.mvc.ResultModel;
import com.forezp.pojo.dao.SeatManage;
import com.forezp.service.SeatManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatManagerController {


    @Autowired
    private SeatManageService seatManageService;

    @PostMapping("/add")
    public ResultModel<List<SeatManage>> save(
            @RequestParam(value = "flightId") Long flightId,
            @RequestParam(value = "batch_number", required = false, defaultValue = "1") Integer batchNumber,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<SeatManage> seatVo = seatManageService.create(batchNumber, flightId);
        return new ResultModel<List<SeatManage>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(seatVo);
    }

    @GetMapping("/list")
    public ResultModel<List<SeatManage>> list(
            @RequestParam(value = "flightId") Long flightId,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<SeatManage> seatVo = seatManageService.list(flightId);
        return new ResultModel<List<SeatManage>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(seatVo);
    }

    @GetMapping("/info")
    public ResultModel<SeatManage> info(
            @RequestParam(value = "seat_number") Long seatId,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        SeatManage seatVo = seatManageService.info(seatId);
        return new ResultModel<SeatManage>().setCode(HttpStatus.OK.value()).setMsg("success").setData(seatVo);
    }


    @PostMapping("/update")
    public ResultModel<SeatManage> update(
            @RequestParam(value = "seat_number") Long seatId,
            @RequestParam(value = "status") Integer status,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        SeatManage seatManage = seatManageService.updateStatus(seatId, status);
        return new ResultModel<SeatManage>().setCode(HttpStatus.OK.value()).setMsg("success").setData(seatManage);
    }

    @PostMapping("/set")
    public ResultModel<SeatManage> set(
            @RequestParam(value = "flightId") Long flightId,
            @RequestParam(value = "seatNumber") Long seatNumber,
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "userNumber") String userNumber,
            HttpServletRequest request, HttpServletResponse response) throws ParseException, MyException {
        SeatManage seatManage = seatManageService.set(flightId, seatNumber, userId, userName, userNumber);
        return new ResultModel<SeatManage>().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @PostMapping("/cancel")
    public ResultModel<SeatManage> cancel(
            @RequestParam(value = "flightId") Long flightId,
            @RequestParam(value = "seatNumber") Long seatNumber,
            HttpServletRequest request, HttpServletResponse response) throws ParseException, MyException {
        SeatManage seatManage = seatManageService.cancel(flightId, seatNumber);
        return new ResultModel<SeatManage>().setCode(HttpStatus.OK.value()).setMsg("success");
    }
}
