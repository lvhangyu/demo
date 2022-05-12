package com.forezp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.pojo.dao.FlightDO;
import com.forezp.pojo.dao.SeatManage;
import org.apache.ibatis.annotations.Mapper;
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
public interface SeatManageMapper extends BaseMapper<SeatManage> {
}
