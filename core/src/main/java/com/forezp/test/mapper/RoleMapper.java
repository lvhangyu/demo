package com.forezp.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.forezp.test.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {


    @Select("select t3.* from user t1 join user_role t2 on t1.id = t2.user_id join role t3 on t2.role_id = t3.id where t1.id = #{Id} ")
    List<Role> getRolesByUserId(Long Id);
}
