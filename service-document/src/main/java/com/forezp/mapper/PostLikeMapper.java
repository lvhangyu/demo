package com.forezp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.pojo.dao.PostLikeDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName PostLikeMapper
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 22:41
 * Version 1.0
 */
@Repository
@Mapper
public interface PostLikeMapper extends BaseMapper<PostLikeDao> {
}
