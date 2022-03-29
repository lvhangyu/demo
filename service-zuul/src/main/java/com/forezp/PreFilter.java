package com.forezp;

import com.alibaba.fastjson.JSONObject;
import com.forezp.mvc.ResultModel;
import com.forezp.util.JwtUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by forezp on 2017/4/8.
 */
@Component
public class PreFilter extends ZuulFilter{

    private static Logger log = LoggerFactory.getLogger(PreFilter.class);
    @Override
    public String filterType() {
        return "pre";
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
        HttpServletRequest request = ctx.getRequest();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));

        if(!isNeedToken(request)){
            log.info("not need token");
            return null;
        }

        Object accessToken = request.getParameter("token");
        if(accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ResultModel resultModel = new ResultModel<>().setCode(HttpStatus.UNAUTHORIZED.value()).setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
                ctx.getResponse().getWriter().write(JSONObject.toJSONString(resultModel));
            }catch (Exception e){}

            return null;
        }


        try {
            String userInfo = JwtUtils.verifyJWTToken(accessToken.toString());
            ctx.addZuulRequestHeader("userInfo",userInfo);
            log.info("ok");
        }catch (Exception e){
            log.warn("token is unauthorized");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ResultModel resultModel = new ResultModel<>().setCode(HttpStatus.UNAUTHORIZED.value()).setMsg(HttpStatus.UNAUTHORIZED.getReasonPhrase());
            try {
                ctx.getResponse().getWriter().write(JSONObject.toJSONString(resultModel));
            } catch (IOException ex) {
            }
        }
        return null;
    }

    private boolean isNeedToken(HttpServletRequest request) {
        if(
                request.getRequestURI().startsWith("/user/login") ||
                request.getRequestURI().startsWith("/user/register")
        ){
            return false;
        }
        return true;
    }
}
