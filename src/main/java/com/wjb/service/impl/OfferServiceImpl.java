package com.wjb.service.impl;

import com.wjb.base.BaseMapper;
import com.wjb.base.BaseServiceImpl;
import com.wjb.mapper.OfferMapper;
import com.wjb.model.Offer;
import com.wjb.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Offer> list() {
        return offerMapper.getOffer();
    }

    @Override
    public List<Offer> getOffer() {
        return offerMapper.getOffer();
    }

}