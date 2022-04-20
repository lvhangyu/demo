package com.forezp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.pojo.dao.PostDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @ClassName PostMapper
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 21:29
 * Version 1.0
 */
@Repository
@Mapper
public interface PostMapper extends BaseMapper<PostDao> {


    /**
     * 帖子点赞+1
     */
    @Update("update ls_post set likes=likes+1 where id = #{id}")
    void like(@Param("id") Long id);

    /**
     * 帖子点赞-1
     */
    @Update("update ls_post set likes=likes-1 where id = #{id}")
    void unlike(@Param("id") Long id);
}
