package com.forezp.service.hystric;

import com.forezp.mvc.ResultModel;
import com.forezp.service.RestFlightService;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceOrderHystric
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 18:03
 * Version 1.0
 */
@Component
public class RestFlightServiceHystric implements RestFlightService {


    @Override
    public ResultModel info(Long id) {
        return new ResultModel<>();
    }

    @Override
    public ResultModel setSeat(Long flightId, Long seatNumber, String mobile, String userName, String userNumber) {
        ResultModel resultModel = new ResultModel<>();
        resultModel.setMsg("座位已被占!");
        resultModel.setCode(400);
        return resultModel;
    }

    @Override
    public ResultModel seatCancel(Long flightId, Long seatNumber) {
        ResultModel resultModel = new ResultModel<>();
        resultModel.setMsg("取消座位失败!");
        resultModel.setCode(400);
        return resultModel;
    }
}
