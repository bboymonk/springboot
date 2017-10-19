package com.wjb.base;

import java.io.Serializable;

/**
 * Created by huangyb on 2016/12/27
 */
public abstract interface BaseService<T,ID extends Serializable> {

    int deleteByPrimaryKey(ID id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(ID id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
