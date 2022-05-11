package com.forezp.controller;

import com.forezp.mvc.CurrentUser;
import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.PostDao;
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

    public static void main(String[] args) {
        PostDao postDao = new PostDao();

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
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.query();
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVoList);
    }

    @GetMapping("/collected")
    public ResultModel<List<PostVo>> collected(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.collected(userInfo);
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVoList);
    }

    @GetMapping("/info/{id}")
    public ResultModel<PostVo> info(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long postId,
            HttpServletRequest request, HttpServletResponse response) {
        PostVo post = postService.info(postId);
        return new ResultModel<PostVo>().setCode(HttpStatus.OK.value()).setMsg("success").setData(post);
    }

    @GetMapping("/myposts")
    public ResultModel<List<PostVo>> myposts(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.queryByUid(userInfo.getId());
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVoList);
    }

    @GetMapping("/liked")
    public ResultModel<List<PostVo>> liked(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.liked(userInfo.getId());
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVoList);
    }
    @GetMapping("/trending")
    public ResultModel<List<PostVo>> trending(
            @CurrentUser UserInfo userInfo,
            HttpServletRequest request, HttpServletResponse response) {
        List<PostVo> postVoList = postService.trending(userInfo);
        return new ResultModel<List<PostVo>>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVoList);
    }

    @PostMapping("/like/{id}")
    public ResultModel giveALike(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long postId,
            HttpServletRequest request, HttpServletResponse response){
        postService.like(postId, userInfo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @PostMapping("/unlike/{id}")
    public ResultModel unLike(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long postId,
            HttpServletRequest request, HttpServletResponse response){
        postService.unlike(postId, userInfo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @PostMapping("/collect/{id}")
    public ResultModel collect(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long postId,
            HttpServletRequest request, HttpServletResponse response){
        postService.collect(postId, userInfo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }

    @PostMapping("/collect/cancel/{id}")
    public ResultModel cancelCollect(
            @CurrentUser UserInfo userInfo,
            @PathVariable("id") Long postId,
            HttpServletRequest request, HttpServletResponse response){
        postService.cancelCollect(postId, userInfo);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }


    /**
     * 评论数+1
     * @param postId
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/comment/add/{id}")
    public ResultModel jiayi(
            @PathVariable("id") Long postId,
            HttpServletRequest request, HttpServletResponse response){
        postService.commentAdd(postId);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }


    /**
     * 评论数+1
     * @param postId
     * @param request
     * @param response
     * @return
     */
    @PostMapping("/comment/cancel/{id}")
    public ResultModel commentCancel(
            @PathVariable("id") Long postId,
            HttpServletRequest request, HttpServletResponse response){
        postService.commentCancel(postId);
        return new ResultModel().setCode(HttpStatus.OK.value()).setMsg("success");
    }
}
