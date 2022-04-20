package com.forezp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.pojo.dao.CommentDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @ClassName FlightMapper
 * @Description TODO
 * @Author sunjie
 * @Date 2022/3/30 10:37
 * Version 1.0
 */
@Mapper
@Repository
public interface CommentMapper extends BaseMapper<CommentDao> {

    /**
     * 评论回复数+1
     */
    @Update("update ls_comment set replys=replys+1 where id = #{id}")
    void autoIncrementReplysNumber(@Param("id") Long id);


    /**
     * 评论回复数-1
     */
    @Update("update ls_comment set replys=replys-1 where id = #{id}")
    void autodecreaseReplysNumber(@Param("id") Long id);

    /**
     * 评论点赞+1
     */
    @Update("update ls_comment set likes=likes+1 where id = #{id}")
    void like(@Param("id") Long id);

    /**
     * 评论点赞-1
     */
    @Update("update ls_comment set likes=likes-1 where id = #{id}")
    void unlike(@Param("id") Long id);
}
