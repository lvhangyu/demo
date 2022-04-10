package com.forezp.controller;

import com.forezp.mvc.ResultModel;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dto.PostDto;
import com.forezp.pojo.vo.PostVo;
import com.forezp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
            @RequestHeader UserInfo userInfo,
            @RequestBody PostDto postDto
    ){
        PostVo postVo = postService.create(userInfo, postDto);
        return new ResultModel<PostVo>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVo);
    }

    @PostMapping("/create")
    public ResultModel<PostVo> update(
            @RequestHeader UserInfo userInfo,
            @RequestBody PostDto postDto
    ){
        PostVo postVo = postService.update(userInfo, postDto);
        return new ResultModel<PostVo>().setCode(HttpStatus.OK.value()).setMsg("success").setData(postVo);
    }
}
