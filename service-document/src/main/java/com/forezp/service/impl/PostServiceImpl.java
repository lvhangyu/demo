package com.forezp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forezp.mapper.CollectionMapper;
import com.forezp.mapper.PostLikeMapper;
import com.forezp.mapper.PostMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CollectionDao;
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

import java.util.*;

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

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public PostVo create(UserInfo userInfo, PostDto postDto) {
        PostDao postDao = new PostDao();
        BeanUtils.copyProperties(postDto, postDao);
        postDao.setCreatorId(userInfo.getId());
        postDao.setStatus(0);
        postDao.setLikes(0);
        postDao.setComments(0);
        postDao.setCollections(0);
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
        for (PostVo p:postVoList) {
            QueryWrapper postLikeQueryWrapper = new QueryWrapper();
            postLikeQueryWrapper.eq("post_id", p.getId());
            postLikeQueryWrapper.eq("user_id", id);
            Boolean liked = postLikeMapper.exists(postLikeQueryWrapper);
            p.setLiked(liked);
            QueryWrapper postCollectQueryWrapper = new QueryWrapper();
            postCollectQueryWrapper.eq("note_id", p.getId());
            postCollectQueryWrapper.eq("type", 0);
            postCollectQueryWrapper.eq("user_id", id);
            Boolean collected = collectionMapper.exists(postCollectQueryWrapper);
            p.setCollected(collected);
        }
        return postVoList;
    }

    @Override
    public List<PostVo> trending(UserInfo userInfo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", PostDao.PASSED);
        queryWrapper.orderByDesc("likes");
        List<PostDao> postDaoList = postMapper.selectList(queryWrapper);
        List<PostVo> postVoList =  BeanUtil.copyToList(postDaoList, PostVo.class, null);
        for (PostVo p:postVoList) {
            QueryWrapper postLikeQueryWrapper = new QueryWrapper();
            postLikeQueryWrapper.eq("post_id", p.getId());
            postLikeQueryWrapper.eq("user_id", userInfo.getId());
            Boolean liked = postLikeMapper.exists(postLikeQueryWrapper);
            p.setLiked(liked);
            QueryWrapper postCollectQueryWrapper = new QueryWrapper();
            postCollectQueryWrapper.eq("note_id", p.getId());
            postCollectQueryWrapper.eq("type", 0);
            postCollectQueryWrapper.eq("user_id", userInfo.getId());
            Boolean collected = collectionMapper.exists(postCollectQueryWrapper);
            p.setCollected(collected);
        }
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

    @Override
    public void collect(Long postId, UserInfo userInfo) {
        postMapper.collect(postId);
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.setNoteId(postId);
        collectionDao.setUserId(userInfo.getId());
        collectionDao.setType(0);
        collectionDao.setCtime(new Date());
        collectionDao.setMtime(new Date());
        collectionMapper.insert(collectionDao);
    }

    @Override
    public void cancelCollect(Long postId, UserInfo userInfo) {
        postMapper.cancelCollect(postId);
        CollectionDao collectionDao = new CollectionDao();
        collectionDao.setNoteId(postId);
        collectionDao.setUserId(userInfo.getId());
        collectionDao.setType(0);
        Map map = new HashMap();
        map.put("note_id", postId);
        map.put("user_id", userInfo.getId());
        map.put("type", 0);
        collectionMapper.deleteByMap(map);
    }

    @Override
    public List<PostVo> collected(UserInfo userInfo) {
        List<PostDao> postDaoList = postMapper.selectList(null);
        List<PostVo> postVoList =  BeanUtil.copyToList(postDaoList, PostVo.class, null);
        List<PostVo> postVoCollectedList = new ArrayList<>(16);
        for (PostVo p:postVoList) {
            QueryWrapper postLikeQueryWrapper = new QueryWrapper();
            postLikeQueryWrapper.eq("post_id", p.getId());
            postLikeQueryWrapper.eq("user_id", userInfo.getId());
            Boolean liked = postLikeMapper.exists(postLikeQueryWrapper);
            p.setLiked(liked);
            QueryWrapper postCollectQueryWrapper = new QueryWrapper();
            postCollectQueryWrapper.eq("note_id", p.getId());
            postCollectQueryWrapper.eq("type", 0);
            postCollectQueryWrapper.eq("user_id", userInfo.getId());
            Boolean collected = collectionMapper.exists(postCollectQueryWrapper);
            p.setCollected(collected);
            if (collected){
                postVoCollectedList.add(p);
            }
        }
        return postVoCollectedList;
    }

    @Override
    public PostVo info(Long postId) {
        PostDao postDao = postMapper.selectById(postId);
        PostVo postVo = new PostVo();
        BeanUtils.copyProperties(postDao, postVo);
        return postVo;
    }

    @Override
    public List<PostVo> liked(Long id) {
        List<PostDao> postDaoList = postMapper.selectList(null);
        List<PostVo> postVoList =  BeanUtil.copyToList(postDaoList, PostVo.class, null);
        List<PostVo> postVoLikedList = new ArrayList<>(16);
        for (PostVo p:postVoList) {
            QueryWrapper postLikeQueryWrapper = new QueryWrapper();
            postLikeQueryWrapper.eq("post_id", p.getId());
            postLikeQueryWrapper.eq("user_id", id);
            Boolean liked = postLikeMapper.exists(postLikeQueryWrapper);
            p.setLiked(liked);
            QueryWrapper postCollectQueryWrapper = new QueryWrapper();
            postCollectQueryWrapper.eq("note_id", p.getId());
            postCollectQueryWrapper.eq("type", 0);
            postCollectQueryWrapper.eq("user_id", id);
            Boolean collected = collectionMapper.exists(postCollectQueryWrapper);
            p.setCollected(collected);
            if (liked){
                postVoLikedList.add(p);
            }
        }
        return postVoLikedList;
    }
}
