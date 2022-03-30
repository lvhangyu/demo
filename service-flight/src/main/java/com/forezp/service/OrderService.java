package com.forezp.service;

import com.forezp.service.hystric.OrderServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 18:00
 * Version 1.0
 */
@FeignClient(value = "service-order",fallback = OrderServiceHystric.class)
public interface OrderService {
    @RequestMapping(value = "/order/query",method = RequestMethod.GET)
    String getQuery();
}
