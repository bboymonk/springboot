package com.wjb.service;


import com.wjb.model.Pic;

/**
 * Created by wjb on 2017/3/13.
 */
public interface PicService {
    int deleteByPrimaryKey(Integer id);

    int insert(Pic record);

    int insertSelective(Pic record);

    Pic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);
}
