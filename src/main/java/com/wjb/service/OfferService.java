package com.wjb.service;

import com.wjb.base.BaseService;
import com.wjb.model.Offer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface OfferService extends BaseService<Offer,Long>{


    public List<Offer> list(@Param("pageNum")Integer pageNum, @Param("size")Integer size);

    public List<Offer> getOffer(@Param("pageNum")Integer pageNum, @Param("size")Integer size);



}
