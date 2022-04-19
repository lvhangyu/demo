package com.forezp.mvc;

import com.alibaba.fastjson.JSON;
import com.forezp.exception.MyException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class GlobalHandlerExceptionResolver implements org.springframework.web.servlet.HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ResultModel resultModel = new ResultModel();
        if (e instanceof MyException) {
            //业务失败
            resultModel.setCode(((MyException) e).getCode()).setMsg(((MyException) e).getErrorMsg());
        } else if (e instanceof NoHandlerFoundException) {
            //接口不存在的情况
            resultModel.setCode(HttpStatus.NOT_FOUND.value()).setMsg("接口 {" + httpServletRequest.getRequestURI() + "} 不存在");
        }else {
            //服务器内部错误
            resultModel.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            if (o instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) o;
                logger.error("接口{} 出现异常 方法:{}.{}() 异常信息:{}",
                        httpServletRequest.getRequestURI(),
                        handlerMethod.getBean().getClass().getName(),
                        handlerMethod.getMethod().getName(),
                        e.getMessage());
                resultModel.setMsg(e.getMessage());
            } else {
                resultModel.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
            logger.error("error: {}",e.getMessage());
            if(null == e.getMessage()){
                resultModel.setMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
        }
        responseResult(httpServletResponse, resultModel);
        return new ModelAndView();
    }

    private String getLocaleMessage(HttpServletRequest httpServletRequest, String targetKey) {
        if(StringUtils.isBlank(targetKey)){
            return "";
        }

        Locale locale = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if(null != cookies){
            for (int i = 0; i < cookies.length; i++) {
                String key = cookies[i].getName();
                if("language".equals(key)){
                    String value =  cookies[i].getValue();
                    if (value.toLowerCase().equals("tw") || value.toLowerCase().equals("zh_tw")){
                        value = "tw";
                    }
                    locale = new Locale(value);
                    break;
                }
            }
        }

        if (locale == null){
            locale = new Locale("zh");
        }

        logger.info("the locale is " + locale.getLanguage());
        String message = "";

        if(StringUtils.isBlank(message)){
            message = targetKey;
        }
        logger.info("message=" + message);
        return message;
    }

    /**
     * 将返回结果信息写入 response
     *
     * @param response
     * @param resultModel
     */
    private void responseResult(HttpServletResponse response, ResultModel resultModel) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        //请求成功200 可设置resultModel.getCode()
        response.setStatus(resultModel.getCode());

        try {
            response.getWriter().write(JSON.toJSONString(resultModel));
        } catch (IOException e) {
            logger.error("将返回结果信息写入 response异常",e);
        }

    }

}
