package com.forezp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.DocumentDao;
import com.forezp.pojo.dto.DocumentDto;
import com.forezp.pojo.vo.DocumentVo;
import com.forezp.service.CommentRestService;
import com.forezp.service.DocumentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName OrderController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/27 18:40
 * Version 1.0
 */
@RestController
@RequestMapping("/document")
public class DocumnetController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CommentRestService commentRestService;



    @PostMapping("/create")
    public ResultModel<DocumentVo> create(
            @CurrentUser UserInfo userInfo,
            @RequestBody DocumentDto documentDto,
            HttpServletRequest request, HttpServletResponse response){
        DocumentDao documentDao = documentService.save(documentDto);
        DocumentVo documentVo = new DocumentVo();
        BeanUtils.copyProperties(documentDao,documentVo);
        return new ResultModel<DocumentVo>().setCode(HttpStatus.OK.value()).setMsg("success").setData(documentVo);
    }


    public static void main(String[] args) {
        DocumentDto documentDto = new DocumentDto();

    }



    @PostMapping("/delete")
    public ResultModel<DocumentVo> delete(
            @CurrentUser UserInfo userInfo,
            @RequestParam("id") Long id,
            HttpServletRequest request, HttpServletResponse response){
        documentService.delete(id);
        return new ResultModel<DocumentVo>().setCode(HttpStatus.OK.value()).setMsg("success");
    }


    @PostMapping("/update")
    public ResultModel<DocumentVo> update(
            @CurrentUser UserInfo userInfo,
            @RequestBody DocumentDto documentDto,
            HttpServletRequest request, HttpServletResponse response){
        documentService.update(documentDto);
        return new ResultModel<DocumentVo>().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @GetMapping("/list")
    public ResultModel<List<DocumentVo>> list(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest request, HttpServletResponse response){
        List<DocumentDao> documentDaoList = documentService.list();
        List<DocumentVo> documentVoList = BeanUtil.copyToList(documentDaoList, DocumentVo.class, null);
        return new ResultModel<List<DocumentVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(documentVoList);
    }

    @PostMapping("/collect/{id}")
    public ResultModel collect(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long documentId,
            HttpServletRequest request, HttpServletResponse response){
        documentService.collect(documentId, userInfo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @PostMapping("/collect/cancel/{id}")
    public ResultModel cancelCollect(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long documentId,
            HttpServletRequest request, HttpServletResponse response){
        documentService.cancelCollect(documentId, userInfo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }
}
