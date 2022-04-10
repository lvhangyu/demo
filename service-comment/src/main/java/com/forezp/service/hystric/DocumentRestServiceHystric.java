package com.forezp.service.hystric;

import com.forezp.service.DocumentRestService;
import org.springframework.stereotype.Component;

/**
 * @ClassName ServiceOrderHystric
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 18:03
 * Version 1.0
 */
@Component
public class DocumentRestServiceHystric implements DocumentRestService {
    @Override
    public String getQuery() {
        return "sorry";
    }
}
