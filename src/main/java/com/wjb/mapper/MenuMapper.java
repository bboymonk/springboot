package com.wjb.mapper;

import com.wjb.base.BaseMapper;
import com.wjb.model.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MenuMapper extends BaseMapper<Menu,Long>{
    List<Menu> getUrl(@Param("id")Long id);
}