package com.wjb.service.impl;

import com.github.pagehelper.PageInfo;
import com.wjb.base.BaseMapper;
import com.wjb.base.BaseServiceImpl;
import com.wjb.mapper.OfferMapper;
import com.wjb.model.Offer;
import com.wjb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/19.
 */
@Service
public class OfferServiceImpl extends BaseServiceImpl<Offer,Long> implements OfferService{
    @Autowired
    private OfferMapper offerMapper;

    @Override
    public BaseMapper getMapper() {
        return offerMapper;
    }


    @Override
    public PageInfo<Offer> getOffer(Map<String,Object> map) {
        List<Offer> offer = offerMapper.getOffer(map);
        return new PageInfo<Offer>(offer);
    }
}
