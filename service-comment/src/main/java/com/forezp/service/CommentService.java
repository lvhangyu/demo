package com.forezp.service;

import com.forezp.mvc.UserInfo;
import com.forezp.pojo.dao.CommentDao;
import com.forezp.pojo.dto.CommentDto;

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

    void delete(Long id);

    List<CommentDao> getListByPostId(Long postId);

}

