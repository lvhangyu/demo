package com.forezp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.pojo.dao.ClazzDao;
import com.forezp.pojo.dao.MajorClazzDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MajorClazzMapper extends BaseMapper<MajorClazzDao> {
}
