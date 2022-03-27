package com.forezp.config;

import com.alibaba.fastjson.JSONObject;
import com.forezp.exception.MyException;
import com.forezp.mvc.ResultModel;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomerErrorDecoder implements ErrorDecoder {

    private static Logger log = LoggerFactory.getLogger(CustomerErrorDecoder.class);
    @Override
    public Exception decode(String s, Response response) {
        if (response.status() != HttpStatus.OK.value()) {
            if (response.status() == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                String errorContent;
                try {
                    errorContent = Util.toString(response.body().asReader());
                    ResultModel resultModel = JSONObject.parseObject(errorContent, ResultModel.class);
                    MyException internalApiException = new MyException(resultModel.getCode(),resultModel.getMsg());
                    return internalApiException;
                } catch (IOException e) {
                    log.error("handle error exception");
                    return new MyException(500, "unknown error");
                }
            }
        }
        return new MyException(500, "unknown error");
    }
}
