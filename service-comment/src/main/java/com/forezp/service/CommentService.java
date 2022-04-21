package com.forezp.service;

import com.forezp.exception.MyException;
import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CommentDao;
import com.forezp.pojo.dto.CommentDto;
import com.forezp.pojo.vo.CommentVo;

import java.util.List;

/**
 * @ClassName FlightService
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:36
 * Version 1.0
 */
public interface CommentService {

    CommentDao save(CommentDto commentDto, UserInfo userInfo);

    void delete(Long id) throws MyException;

    void like(Long id, UserInfo userInfo);

    List<CommentVo> getListByPostId(Long postId, Long commentId, UserInfo userInfo);

    void unlike(Long commentId, UserInfo userInfo);
}

