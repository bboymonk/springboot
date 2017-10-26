package com.wjb.mapper;

import com.wjb.base.BaseMapper;
import com.wjb.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper extends BaseMapper<User,Long>{

    User login(@Param("username")String username,@Param("password")String password);



}