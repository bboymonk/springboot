package com.wjb.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyb on 2016/12/27
 */
public interface BaseMapper<T,ID extends Serializable> {
    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    int getCount();

    <T> List<T> selectPagers(Map<String, Object> map);
}
