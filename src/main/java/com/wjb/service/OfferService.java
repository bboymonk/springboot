package com.wjb.service;

import com.github.pagehelper.PageInfo;
import com.wjb.mapper.OfferMapper;
import com.wjb.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Administrator on 2017/10/16.
 */
@Service
public class OfferService {
    @Autowired
    private OfferMapper offerMapper;

    public PageInfo<Offer> getOffer(String startDate, String endDate){
        List<Offer> offer = offerMapper.getOffer(startDate,endDate);
        return new PageInfo<Offer>(offer);
    }

//    public PageInfo<Offer> getHistoryOffer(String startDate, String endDate, Integer countryId, Integer type){
//        List<Offer> offer = offerMapper.getHistoryOffer(startDate,endDate,countryId,type);
//        return new PageInfo<Offer>(offer);
//    }
//
//
//
//    @Transactional
//    public Integer upOffer(Offer offer,Integer id,Integer type){
//        Integer s = 0;
//        Integer i = offerMapper.upOffer(id, type);
//        if (i > 0){
//            s = offerMapper.addOffer(offer);
//        }
//        return s;
//    }
//
//
//    public Integer delete(Integer id){
//        return offerMapper.deleteOffer(id);
//    }

}
