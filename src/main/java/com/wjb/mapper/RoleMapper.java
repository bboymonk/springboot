package com.wjb.mapper;

import com.wjb.base.BaseMapper;
import com.wjb.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<Role,Long>{
    List<Role> roleList(@Param("uie")Integer uid);
}