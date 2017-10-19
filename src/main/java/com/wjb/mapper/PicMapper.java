package com.wjb.mapper;


import com.wjb.model.Pic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pic record);

    int insertSelective(Pic pic);

    Pic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);
}