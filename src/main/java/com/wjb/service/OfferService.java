package com.wjb.service;

import com.wjb.base.BaseService;
import com.wjb.model.Offer;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface OfferService extends BaseService<Offer,Long>{


    public List<Offer> list();

    public List<Offer> getOffer();



}
