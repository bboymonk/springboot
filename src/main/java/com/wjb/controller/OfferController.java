package com.wjb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjb.mapper.OfferMapper;
import com.wjb.model.Offer;
import com.wjb.service.OfferService;
import com.wjb.util.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/10/16.
 */
@Controller
@RequestMapping("offer")
public class OfferController {
    @Autowired
    private OfferService offerService;
    @Autowired
    private OfferMapper offerMapper;

    @GetMapping("list")
    public String list(){
        return "offer/offer";
    }

    /**
     * 获取最新Offer列表
    *@Author:
    *@params:
    *@Date:14:14 2017/10/16
    */
    @ResponseBody
    @GetMapping("getOffer")
    public SimpleResult getOffer(Integer pageNum, Integer size, String startDate, String endDate){
        PageHelper.startPage(pageNum == null ? 1 : pageNum, size == null ? 3 : size);
        try {
            PageInfo<Offer> offer = offerService.getOffer(startDate,endDate);
                return new SimpleResult(offer);
        } catch (Exception e) {
            e.printStackTrace();
            return new SimpleResult(-1,"query error",null);
        }
    }

//    /**
//     * 获取历史Offer列表
//    *@Author:
//    *@params:
//    *@Date:14:14 2017/10/16
//    */
//    @ResponseBody
//    @GetMapping("historyOffer")
//    public SimpleResult historyOffer(Integer pageNum, Integer size, String startDate, String endDate,Integer countryId,Integer type){
//        type = type == null ? 0 : type;
//        countryId = countryId == null ? null :countryId;
//        PageHelper.startPage(pageNum == null ? 1 : pageNum, size == null ? 3 : size);
//        try {
//            PageInfo<Offer> offer = offerService.getHistoryOffer(startDate,endDate,countryId,type);
//            return new SimpleResult(offer);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new SimpleResult(-1,"query error",null);
//        }
//    }
//
//
//
//
//    /**
//     * 更新Offer
//    *@Author:
//    *@params:
//    *@Date:14:16 2017/10/16
//    */
//    @ResponseBody
//    @PostMapping("upOffer")
//    public SimpleResult updateOffer(@RequestBody Offer offer){
//        offer.setType(0);
//        if (null == offer){
//            return new SimpleResult(-1,"offer is null",null);
//        }
//        try {
//            offerService.upOffer(offer,offer.getId(),1);
//            return new SimpleResult(0,"修改成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new SimpleResult(-1,"修改异常");
//        }
//    }
//
//    /**
//     * 删除offer
//    *@Author:
//    *@params:
//    *@Date:18:02 2017/10/16
//    */
//    @ResponseBody
//    @PostMapping("delete")
//    public SimpleResult delete(@RequestBody Offer offer){
//        try {
//            offerService.delete(offer.getId());
//            return new SimpleResult(0,"删除成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new SimpleResult(-1,"删除失败");
//        }
//    }

}
