package com.wjb.service.impl;

import com.wjb.mapper.PicMapper;
import com.wjb.model.Pic;
import com.wjb.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wjb on 2017/3/13.
 */
@Service
public class PicServiceImpl implements PicService {
    @Autowired
    private PicMapper picMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(Pic record) {
        return 0;
    }

    @Override
    public int insertSelective(Pic pic) {
        int result = picMapper.insertSelective(pic);
        return result;
    }

    @Override
    public Pic selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Pic record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Pic record) {
        return 0;
    }
}
