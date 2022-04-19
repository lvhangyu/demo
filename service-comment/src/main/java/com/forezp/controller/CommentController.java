package com.forezp.controller;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSONObject;
import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CommentDao;
import com.forezp.pojo.dto.CommentDto;
import com.forezp.pojo.vo.CommentVo;
import com.forezp.service.CommentService;
import com.forezp.service.DocumentRestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName FlightController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:15
 * Version 1.0
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private DocumentRestService documentRestService;


    @PostMapping("/create")
    public ResultModel create(
            @CurrentUser UserInfo userInfo,
            @RequestBody CommentDto commentDto,
            HttpServletRequest request, HttpServletResponse response){
        CommentDao commentDao = commentService.save(commentDto, userInfo);
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(commentDao, commentVo);
        return new ResultModel<CommentVo>().setCode(HttpStatus.OK.value()).setMsg("success").setData(commentVo);
    }

    public static void main(String[] args) {
        CommentDto commentDto = new CommentDto();
        commentDto.setPostId(0L);
        commentDto.setParentId(0L);
        commentDto.setContent("评论");
        System.out.println(JSONObject.toJSONString(commentDto));

    }

    @PostMapping("/delete/{id}")
    public ResultModel delete(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long commentId,
            HttpServletRequest request, HttpServletResponse response){
        commentService.delete(commentId);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }



    @GetMapping("/list")
    public ResultModel<List<CommentVo>> list(
            @CurrentUser UserInfo userInfo,
            @RequestParam("post_id") Long postId,
            @RequestParam(value = "comment_id", required = false) Long commentId,
            HttpServletRequest request, HttpServletResponse response){
        List<CommentDao> commentDaoList = commentService.getListByPostId(postId, commentId);
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.ignoreCase();
        List<CommentVo> commentVoList = BeanUtil.copyToList(commentDaoList, CommentVo.class,copyOptions);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success").setData(commentVoList);
    }

    @PostMapping("/like/{id}")
    public ResultModel giveALike(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long commentId,
            HttpServletRequest request, HttpServletResponse response){
        commentService.like(commentId, userInfo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

}
