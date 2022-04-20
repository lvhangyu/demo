package com.forezp.service;

import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dto.PostDto;
import com.forezp.pojo.vo.PostVo;

import java.util.List;

/**
 * @ClassName PostService
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 21:32
 * Version 1.0
 */
public interface PostService {
    PostVo create(UserInfo userInfo, PostDto postDto);

    PostVo update(UserInfo userInfo, PostDto postDto);

    void delete(Long id);

    List<PostVo> query();

    List<PostVo> queryByUid(Long id);

    List<PostVo> trending();

    void like(Long postId, UserInfo userInfo);
}
