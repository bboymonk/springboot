package com.wjb.mapper;

import com.wjb.model.Offer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface OfferMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Offer record);

    int insertSelective(Offer record);

    Offer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Offer record);

    int updateByPrimaryKey(Offer record);

    public List<Offer> getOffer(@Param("startDate")String startDate, @Param("endDate")String endDate);

}