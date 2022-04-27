package com.forezp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.forezp.exception.MyException;
import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.vo.ClazzVo;
import com.forezp.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 18:40
 * Version 1.0
 */
@RestController
@RequestMapping("/class")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @PostMapping(value="/import")
    public void clazzImport(
            @RequestParam(required = true, value="major_number") String majorNumber,
            @RequestParam(required = true, value="major_name") String majorName,
            @CurrentUser UserInfo userInfo,
            HttpServletRequest req, HttpServletResponse resp) throws Exception{
        MultipartFile file = null;
        if(req instanceof MultipartHttpServletRequest) {
            file = ((MultipartHttpServletRequest) req).getFile("filedata");
        }else {
            throw new MyException("req is not MultipartHttpServletRequest", 400 ,"import error");
        }
        this.checkRequestParams(file);
        String fileType = "";
        //读取文件内容
        InputStream is = null;
        ClazzVo clazzVo = new ClazzVo();
        try {
            is = file.getInputStream();
            String name = file.getOriginalFilename();
            fileType = name.substring(name.lastIndexOf(".")+1);
            //execelIS为InputStream流
            byte[] buf = IOUtils.toByteArray(is);
            //在需要用到InputStream的地方再封装成InputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
            clazzVo = clazzService.importClazz(byteArrayInputStream,userInfo,fileType,majorNumber,majorName);

        } finally {
            IOUtils.closeQuietly(is);
        }
        String retStr = clazzVo.toString();
        resp.setContentType("application/json; charset=utf-8");
        String text = "";
        if(retStr instanceof String){
            text = retStr;
        }else{
            text = JSON.toJSONString(retStr, SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullStringAsEmpty);
        }
        resp.getWriter().write(text);
    }

    /**
     * 校验参数
     * @param file
     * @throws Exception
     */
    private void checkRequestParams(MultipartFile file) throws MyException {
        if (file == null) {
            throw new MyException("filedata is null", 400, "error param");
        }
        String fileType = "";
        String name = file.getOriginalFilename();
        fileType = name.substring(name.lastIndexOf(".")+1);
        if(!"xls".equalsIgnoreCase(fileType) && !"xlsx".equalsIgnoreCase(fileType)) {
            throw new MyException("file type is not excel", 400, "error param");
        }
    }

}
