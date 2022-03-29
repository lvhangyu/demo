package com.forezp;

import com.alibaba.fastjson.JSONObject;
import com.forezp.mvc.ResultModel;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ErrorFilter.class);
    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.info("进入异常过滤器");
        ctx.setResponseBody(JSONObject.toJSONString(new ResultModel<>().setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).setMsg("zuul error")));
        return null;
    }
}
