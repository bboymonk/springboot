package com.wjb.base;


import java.io.Serializable;

/**
 * Created by huangyb on 2016/12/27
 */

public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T,ID> {

    public abstract BaseMapper getMapper();

    @Override
    public int deleteByPrimaryKey(ID id){
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record){
        return getMapper().insert(record);
    }

    @Override
    public int insertSelective(T record){
        return getMapper().insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(ID id){
        return (T)getMapper().selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(T record){
        return getMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record){
        return getMapper().updateByPrimaryKey(record);
    }


}
