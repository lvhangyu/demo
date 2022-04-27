package com.forezp.controller;

import com.forezp.exception.MyException;
import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.vo.MajorClazzVo;
import com.forezp.pojo.vo.MajorVo;
import com.forezp.service.MajorClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.commons.io.IOUtils;
import cn.hutool.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    private MajorClazzService majorClazzService;

    @PostMapping(value="/import")
    public ResultModel clazzImport(
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
        MajorClazzVo majorClazzVo = new MajorClazzVo();
        try {
            is = file.getInputStream();
            String name = file.getOriginalFilename();
            fileType = name.substring(name.lastIndexOf(".")+1);
            //execelIS为InputStream流
            byte[] buf = IOUtils.toByteArray(is);
            //在需要用到InputStream的地方再封装成InputStream
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
            majorClazzVo = majorClazzService.importMajorClazz(byteArrayInputStream,userInfo,fileType,majorNumber,majorName);

        } finally {
            IOUtils.closeQuietly(is);
        }
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(majorClazzVo);
    }


    @GetMapping(value="/query")
    public ResultModel<List<MajorClazzVo>> query(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest req, HttpServletResponse resp) throws Exception{
        List<MajorClazzVo> majorClazzVoList = majorClazzService.query();
        return new ResultModel<List<MajorClazzVo>>().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(majorClazzVoList);
    }

    @GetMapping(value="/myclass")
    public ResultModel<MajorClazzVo> myclass(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest req, HttpServletResponse resp) throws Exception{
        MajorClazzVo majorClazzVo = majorClazzService.myclass(userInfo);
        return new ResultModel<MajorClazzVo>().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(majorClazzVo);
    }

    @GetMapping(value="/majors")
    public ResultModel<List<MajorVo>> majorQuery(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest req, HttpServletResponse resp) throws Exception{
        List<MajorVo> majorVoList = majorClazzService.majors();
        return new ResultModel<List<MajorVo>>().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(majorVoList);
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
