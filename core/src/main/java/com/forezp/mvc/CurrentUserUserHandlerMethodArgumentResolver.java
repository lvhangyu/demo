package com.forezp.mvc;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class CurrentUserUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static Logger log = LoggerFactory.getLogger(CurrentUserUserHandlerMethodArgumentResolver.class);

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean a = parameter.getParameterType().isAssignableFrom(UserInfo.class);
//        CurrentUser b = parameter.getMethodAnnotation(CurrentUser.class);
        return a;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String userInfo = nativeWebRequest.getHeader("userInfo");
        UserInfo user = JSONObject.parseObject(userInfo, UserInfo.class);
        log.info("当前用户:{}", userInfo);
        return user;
    }
}
