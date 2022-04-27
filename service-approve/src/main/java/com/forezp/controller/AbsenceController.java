package com.forezp.controller;

import cn.hutool.http.HttpStatus;
import com.forezp.mvc.ResultModel;
import com.forezp.pojo.dto.AbsenceDto;
import com.forezp.pojo.vo.AbsenceVo;
import com.forezp.service.AbsenceService;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/absence")
public class AbsenceController {

    @Autowired
    private AbsenceService absenceService;

    @PostMapping("/create")
    public ResultModel<AbsenceVo> create(
            @RequestBody AbsenceDto absenceDto,
            HttpRequest httpRequest, HttpResponse httpResponse
    ){
        AbsenceVo absenceVo = absenceService.create(absenceDto);
        return new ResultModel<AbsenceVo>().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVo);
    }

    @PostMapping("/delete/{id}")
    public ResultModel delete(
           @PathVariable(value = "id") Long id,
            HttpRequest httpRequest, HttpResponse httpResponse
    ){
        absenceService.delete(id);
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss");
    }

    @PostMapping("/update")
    public ResultModel update(
            @RequestBody AbsenceDto absenceDto,
            HttpRequest httpRequest, HttpResponse httpResponse
    ){
        AbsenceVo absenceVo =  absenceService.update(absenceDto);
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVo);
    }

    @PostMapping("/query")
    public ResultModel<List<AbsenceVo>> query(
            HttpRequest httpRequest, HttpResponse httpResponse
    ){
        List<AbsenceVo> absenceVoList =  absenceService.query();
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVoList);
    }

    @PostMapping("/search")
    public ResultModel<List<AbsenceVo>> search(
            @RequestParam(required = false, value = "absenceTime") Date absenceTime,
            @RequestParam(required = false, value = "majorName") String majorName,
            @RequestParam(required = false, value = "reason") String reason,
            @RequestParam(required = false, value = "status") Integer status,
            @RequestParam(required = false, value = "key") Integer key,
            @RequestParam(required = false, value = "order") Integer order,
            HttpRequest httpRequest, HttpResponse httpResponse
    ){
        List<AbsenceVo> absenceVoList =  absenceService.search(absenceTime, majorName, reason, status, key, order);
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVoList);
    }
}
