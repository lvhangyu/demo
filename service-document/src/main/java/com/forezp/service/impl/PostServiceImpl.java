package com.forezp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.PostLikeMapper;
import com.forezp.mapper.PostMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.PostDao;
import com.forezp.pojo.dto.PostDto;
import com.forezp.pojo.vo.PostVo;
import com.forezp.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName PostServiceImpl
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 21:33
 * Version 1.0
 */
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostLikeMapper postLikeMapper;

    @Override
    public PostVo create(UserInfo userInfo, PostDto postDto) {
        PostDao postDao = new PostDao();
        BeanUtils.copyProperties(postDto, postDao);
        postDao.setCreatorId(userInfo.getId());
//        QueryWrapper queryWrapper = new QueryWrapper<>();
//        queryWrapper.
        postDao.setLikes(0);
        postDao.setComments(0);
        postDao.setConllections(0);
        postMapper.insert(postDao);
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(postDao, postVo);
        return postVo;
    }

    @Override
    public PostVo update(UserInfo userInfo, PostDto postDto) {
        PostDao postDao = postMapper.selectById(postDto.getId());
        postDao.setTitle(postDto.getTitle());
        BeanUtils.copyProperties(postDto, postDao);
        return null;
    }
}
