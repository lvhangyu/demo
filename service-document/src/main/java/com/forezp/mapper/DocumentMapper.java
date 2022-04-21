package com.forezp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.pojo.dao.DocumentDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface DocumentMapper extends BaseMapper<DocumentDao> {
    /**
     * 帖子收藏+1
     */
    @Update("update ls_document set collections=collections+1 where id = #{id}")
    void collect(@Param("id") Long id);

    /**
     * 帖子收藏-1
     */
    @Update("update ls_document set collections=collections-1 where id = #{id}")
    void cancelCollect(@Param("id") Long id);

}
