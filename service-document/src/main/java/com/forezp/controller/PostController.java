package com.forezp.controller;

import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dto.PostDto;
import com.forezp.pojo.vo.PostVo;
import com.forezp.service.PostService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName PostController
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 21:27
 * Version 1.0
 */
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/create")
    public ResultModel<PostVo> create(
            @CurrentUser UserInfo userInfo,
            @RequestBody PostDto postDto,
            HttpServletRequest request, HttpServletResponse response
    ) {
        PostVo postVo = postService.create(userInfo, postDto);
        return new ResultModel<PostVo>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVo);
    }

    @PostMapping("/update")
    public ResultModel<PostVo> update(
            @CurrentUser UserInfo userInfo,
            @RequestBody PostDto postDto,
            HttpServletRequest request, HttpServletResponse response) {
        PostVo postVo = postService.update(userInfo, postDto);
        return new ResultModel<PostVo>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVo);
    }

    @DeleteMapping("/delete")
    public ResultModel<PostVo> delete(
            @CurrentUser UserInfo userInfo,
            @RequestParam("id") Long id,
            HttpServletRequest request, HttpServletResponse response) {
        postService.delete(id);
        return new ResultModel<PostVo>().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @GetMapping("/query")
    public ResultModel<List<PostVo>> query(
            @CurrentUser UserInfo userInfo,
            @RequestParam("id") Long id,
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.query();
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @GetMapping("/myposts")
    public ResultModel<List<PostVo>> myposts(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.queryByUid(userInfo.getId());
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVoList);
    }

    @GetMapping("/trending")
    public ResultModel<List<PostVo>> trending(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.trending();
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVoList);
    }
}
