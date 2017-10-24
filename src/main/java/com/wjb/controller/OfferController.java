package com.wjb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjb.base.BaseController;
import com.wjb.mapper.OfferMapper;
import com.wjb.model.Offer;
import com.wjb.service.OfferService;
import com.wjb.util.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */
@Controller
@RequestMapping("offer")
public class OfferController extends BaseController{
    @Autowired
    private OfferService offerService;


    @GetMapping("list")
    public String list(){
        return "pagination/angularjs_bootstrap";
    }


    @ResponseBody
    @GetMapping("getList")
    public String getList(Integer pageNum, Integer size){
        try {
            List<Offer> list = offerService.list();
            return successOrFail(true,list,"error");
        } catch (Exception e) {
            e.printStackTrace();
            return successOrFail(false,null,"error");
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
//            PageInfo<OfferService> offer = offerService.getHistoryOffer(startDate,endDate,countryId,type);
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
//    public SimpleResult updateOffer(@RequestBody OfferService offer){
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
//    public SimpleResult delete(@RequestBody OfferService offer){
//        try {
//            offerService.delete(offer.getId());
//            return new SimpleResult(0,"删除成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new SimpleResult(-1,"删除失败");
//        }
//    }

}
