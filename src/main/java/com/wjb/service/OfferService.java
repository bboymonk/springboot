package com.wjb.service;

import com.github.pagehelper.PageInfo;
import com.wjb.base.BaseService;
import com.wjb.model.Offer;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface OfferService extends BaseService<Offer,Long>{

    public PageInfo<Offer> getOffer(Map<String,Object> map);


}
