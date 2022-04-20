package com.forezp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.PostLikeMapper;
import com.forezp.mapper.PostMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.DocumentDao;
import com.forezp.pojo.dao.PostDao;
import com.forezp.pojo.dao.PostLikeDao;
import com.forezp.pojo.dto.PostDto;
import com.forezp.pojo.vo.PostVo;
import com.forezp.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        postDao.setStatus(0);
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
        PostDao postDao = new PostDao();
        postDao.setMtime(new Date());
        BeanUtils.copyProperties(postDto, postDao);
        postMapper.updateById(postDao);
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(postDao, postVo);
        return postVo;
    }

    @Override
    public void delete(Long id) {
        postMapper.deleteById(id);
    }

    @Override
    public List<PostVo> query() {
        List<PostDao> postDaoList = postMapper.selectList(null);
        List<PostVo> postVoList =  BeanUtil.copyToList(postDaoList, PostVo.class, null);
        return postVoList;
    }

    @Override
    public List<PostVo> queryByUid(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("creator_id", id);
        List<PostDao> postDaoList = postMapper.selectList(queryWrapper);
        List<PostVo> postVoList =  BeanUtil.copyToList(postDaoList, PostVo.class, null);
        return postVoList;
    }

    @Override
    public List<PostVo> trending() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", PostDao.PASSED);
        queryWrapper.orderByDesc("likes");
        List<PostDao> postDaoList = postMapper.selectList(queryWrapper);
        List<PostVo> postVoList =  BeanUtil.copyToList(postDaoList, PostVo.class, null);
        return postVoList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void like(Long postId, UserInfo userInfo) {
        postMapper.like(postId);
        PostLikeDao postLikeDao = new PostLikeDao();
        postLikeDao.setCtime(new Date());
        postLikeDao.setMtime(new Date());
        postLikeDao.setPostId(postId);
        postLikeDao.setUserId(userInfo.getId());
        postLikeMapper.insert(postLikeDao);
    }

    @Override
    public void unlike(Long postId, UserInfo userInfo) {
        //-1
        postMapper.unlike(postId);
        Map map1 = new HashMap();
        map1.put("post_id", postId);
        map1.put("user_id", userInfo.getId());
        postLikeMapper.deleteByMap(map1);
    }
}
