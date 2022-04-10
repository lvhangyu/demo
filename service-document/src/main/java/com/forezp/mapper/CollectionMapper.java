package com.forezp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.pojo.dao.CollectionDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CollectionMapper
 * @Description TODO
 * @Author sunjie
 * @Date 2022/4/10 22:39
 * Version 1.0
 */
@Mapper
@Repository
public interface CollectionMapper extends BaseMapper<CollectionDao> {
}
