package com.wjb.mapper;

import com.wjb.model.Provider;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProviderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);
}