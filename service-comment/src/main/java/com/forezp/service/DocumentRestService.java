package com.forezp.service;

import com.forezp.service.hystric.DocumentRestServiceHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName OrderService
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 18:00
 * Version 1.0
 */
@Service
@FeignClient(value = "service-order",fallback = DocumentRestServiceHystric.class)
public interface DocumentRestService {
    @GetMapping(value = "/query")
    String getQuery();
}
