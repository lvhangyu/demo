package com.forezp.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.forezp.mapper.CommentMapper;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CommentDao;
import com.forezp.pojo.dto.CommentDto;
import com.forezp.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public CommentDao save(CommentDto commentDto, UserInfo userInfo) {
        CommentDao commentDao = new CommentDao();
        BeanUtils.copyProperties(commentDto, commentDao);
        commentDao.setLikes(0);
        commentDao.setUserId(userInfo.getId());
        commentDao.setCtime(new Date());
        commentDao.setMtime(new Date());
        commentMapper.insert(commentDao);
        return commentDao;
    }

    @Override
    public void delete(Long id) {
        commentMapper.deleteById(id);
    }

    @Override
    public List<CommentDao> getListByPostId(Long postId) {
        Map<String,Object> map = new HashMap<>();
        map.put("post_id", postId);
        List<CommentDao> commentDaoList = commentMapper.selectByMap(map);
        return commentDaoList;
    }
}
