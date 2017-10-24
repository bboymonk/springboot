package com.wjb.mapper;

import com.wjb.base.BaseMapper;
import com.wjb.model.Offer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
public interface OfferMapper extends BaseMapper<Offer,Long>{


    List<Offer> list();

    List<Offer> getOffer();
}