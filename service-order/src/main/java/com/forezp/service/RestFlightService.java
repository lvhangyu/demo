package com.forezp.service;

import com.forezp.service.hystric.RestFlightServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(value = "/create")
    String getQuery(@RequestHeader("userInfo") String userInfo,
                    @RequestParam("accountId") Long accountId);
}
