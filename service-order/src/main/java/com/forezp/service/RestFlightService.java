package com.forezp.service;

import com.forezp.mvc.ResultModel;
import com.forezp.service.hystric.RestFlightServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 18:00
 * Version 1.0
 */
@Service
@FeignClient(value = "service-flight",fallback = RestFlightServiceHystric.class)
public interface RestFlightService {
    @GetMapping(value = "/info")
    ResultModel info(@RequestParam(value = "id") Long id);

    @PostMapping(value = "/seat/set")
    ResultModel setSeat(@RequestParam(value = "flightId") Long flightId,
                        @RequestParam(value = "seatNumber") Long seatNumber,
                        @RequestParam(value = "mobile") String mobile,
                        @RequestParam(value = "userName") String userName,
                        @RequestParam(value = "userNumber") String userNumber
    );

    @PostMapping(value = "/seat/cancel")
    ResultModel seatCancel(@RequestParam(value = "flightId") Long flightId,
                        @RequestParam(value = "seatNumber") Long seatNumber
    );
}
