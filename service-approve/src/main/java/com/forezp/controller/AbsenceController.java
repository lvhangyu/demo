package com.forezp.controller;

import cn.hutool.http.HttpStatus;
import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dto.AbsenceDto;
import com.forezp.pojo.vo.AbsenceVo;
import com.forezp.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            @CurrentUser UserInfo userInfo,
            @RequestBody AbsenceDto absenceDto,
            HttpServletRequest req, HttpServletResponse resp
    ){
        AbsenceVo absenceVo = absenceService.create(absenceDto, userInfo);
        return new ResultModel<AbsenceVo>().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVo);
    }

    @PostMapping("/delete/{id}")
    public ResultModel delete(
           @PathVariable(value = "id") Long id,
           HttpServletRequest req, HttpServletResponse resp
    ){
        absenceService.delete(id);
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss");
    }

    @PostMapping("/update")
    public ResultModel update(
            @RequestBody AbsenceDto absenceDto,
            HttpServletRequest req, HttpServletResponse resp
    ){
        AbsenceVo absenceVo =  absenceService.update(absenceDto);
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVo);
    }

    @GetMapping("/query")
    public ResultModel<List<AbsenceVo>> query(
            HttpServletRequest req, HttpServletResponse resp
    ){
        List<AbsenceVo> absenceVoList =  absenceService.query();
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVoList);
    }

    @GetMapping("/my_absence")
    public ResultModel<List<AbsenceVo>> myAbsence(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest req, HttpServletResponse resp
    ){
        List<AbsenceVo> absenceVoList =  absenceService.myAbsence(userInfo);
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
            HttpServletRequest req, HttpServletResponse resp
    ){
        List<AbsenceVo> absenceVoList =  absenceService.search(absenceTime, majorName, reason, status, key, order);
        return new ResultModel().setCode(HttpStatus.HTTP_OK).setMsg("seccuss").setData(absenceVoList);
    }
}
