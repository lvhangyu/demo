package com.forezp.controller;

import com.forezp.mvc.ResultModel;
import com.forezp.pojo.dto.FlightDTO;
import com.forezp.pojo.vo.FlightVO;
import com.forezp.service.FlightService;
import com.forezp.service.OrderService;
import com.forezp.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName FlightController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:15
 * Version 1.0
 */
@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultModel<FlightVO> save(
            @RequestBody FlightDTO flightDTO,
            HttpServletRequest request, HttpServletResponse response) throws ParseException {
        String ss = orderService.getQuery();
        System.out.println(ss);
        FlightVO flightVO = flightService.save(flightDTO);
        return new ResultModel<FlightVO>().setCode(HttpStatus.OK.value()).setMsg("success").setData(flightVO);
    }

    public static void main(String[] args) throws ParseException {
        String date1 = "2022-03-30 18:49:00";
        String date2 = "2022-03-30 18:22:00";
        String date = DateUtil.getDurationByDateStr(date1,date2,DateUtil.dateFormat[1]);
        System.out.println(date);
    }
}
