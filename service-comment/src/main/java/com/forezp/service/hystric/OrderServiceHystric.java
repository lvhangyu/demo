package com.forezp.service.hystric;

import com.forezp.service.OrderService;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceOrderHystric
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 18:03
 * Version 1.0
 */
@Component
public class OrderServiceHystric implements OrderService {
    @Override
    public String getQuery() {
        return "sorry";
    }
}
