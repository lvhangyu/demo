package com.forezp.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.bean.BeanUtil;
import com.forezp.exception.MyException;
import com.forezp.mapper.CommentLikeMapper;
import com.forezp.mapper.CommentMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CommentDao;
import com.forezp.pojo.dao.CommentLikeDao;
import com.forezp.pojo.dto.CommentDto;
import com.forezp.pojo.vo.CommentVo;
import com.forezp.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FlightServiceImpl
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:36
 * Version 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentLikeMapper commentLikeMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommentDao save(CommentDto commentDto, UserInfo userInfo) {
        //评论的回复数量+1
        if(commentDto.getParentId() != null){
            commentMapper.autoIncrementReplysNumber(commentDto.getParentId());
        }
        CommentDao commentDao = new CommentDao();
        BeanUtils.copyProperties(commentDto, commentDao);
        commentDao.setLikes(0);
        commentDao.setReplys(0);
        commentDao.setUserId(userInfo.getId());
        commentDao.setCtime(new Date());
        commentDao.setMtime(new Date());
        commentMapper.insert(commentDao);
        return commentDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long delete(Long id) throws MyException {
        //找到父级评论 评论数-1
        CommentDao commentDao = commentMapper.selectById(id);
        if(null == commentDao){
            throw new MyException(400, "评论不存在");
        }
        commentMapper.autodecreaseReplysNumber(commentDao.getParentId());
        commentMapper.deleteById(id);
        return commentDao.getPostId();
    }

    @Override
    public void like(Long id, UserInfo userInfo) {
        //评论点赞数+1
        commentMapper.like(id);
        //评论点赞中间表
        CommentLikeDao commentLikeDao = new CommentLikeDao();
//        commentLikeDao.setPostId(0L);
        commentLikeDao.setCommentId(id);
        commentLikeDao.setUserId(userInfo.getId());
        commentLikeMapper.insert(commentLikeDao);
        //todo insert
    }

    @Override
    public List<CommentVo> getListByPostId(Long postId, Long commentId, UserInfo userInfo) {
        Map<String,Object> map = new HashMap<>();
        map.put("post_id", postId);
        map.put("parent_id", commentId);
        List<CommentDao> commentDaoList = commentMapper.selectByMap(map);
        List<CommentVo> commentVoList = BeanUtil.copyToList(commentDaoList, CommentVo.class,null);
        //判断是否点赞过
        for(CommentVo commentVo : commentVoList){
            Map map1 = new HashMap();
            map1.put("comment_id",commentVo.getId());
            map1.put("user_id",userInfo.getId());
            List<CommentLikeDao> commentLikeDaoList = commentLikeMapper.selectByMap(map1);
            if(commentLikeDaoList != null && commentLikeDaoList.size() > 0){
                commentVo.setLiked(true);
            }
        }


        return commentVoList;
    }

    @Override
    public void unlike(Long commentId, UserInfo userInfo) {
        commentMapper.unlike(commentId);
        Map map1 = new HashMap();
        map1.put("comment_id",commentId);
        map1.put("user_id",userInfo.getId());
        commentLikeMapper.deleteByMap(map1);
    }
}
