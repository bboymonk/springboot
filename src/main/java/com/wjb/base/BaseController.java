package com.wjb.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by hyb on 16/7/26.
 */
public class BaseController {
    public  Map<String,Object> map =new HashMap<String,Object>();
    public static final Map<String,Object> hasSessionMap;

    static {
        hasSessionMap = new HashMap<String,Object>();
}
    /**
     * 成功
     * @return
     */
    public static String SUCCESS(String code){
        JSONObject json = new JSONObject();
        json.put("MSG","true");
        json.put("code",code);
        json.put("DATA","成功");
        return  json.toString();
    }

    /**
     * 失败
     */
    public static String FAIL(String code){
        JSONObject json = new JSONObject();
        json.put("MSG","false");
        json.put("code",code);
        json.put("DATA","失败");
        return  json.toString();
    }


    /**
     * 混合判断输出
     */
    public static String SUCCESS_FAIL(boolean  expr,String code){
        JSONObject json = new JSONObject();
        if(expr){
            json.put("MSG","true");
            json.put("DATA","成功");
        }else{
            json.put("MSG","false");
            json.put("DATA","失败");
        }
        json.put("code",code);
        return  json.toString();
    }

    /**
     * 混合判断输出  整体抛出
     */
    public static String successOrFail(boolean  expr, Object success, Object error){
        JSONObject json = new JSONObject();
        if(expr){
            json.put("MSG","true");
            json.put("DATA",success);
        }else{
            json.put("MSG","false");
            json.put("DATA",error);
        }
        return json.toString();
    }


    /**
     * 混合判断输出  整体抛出
     */
    public static String SUCCESS_FAIL_N(boolean expr, Object success, Object error) {
        JSONObject json = new JSONObject();
        if (expr) {
            json.put("data", success);
            json.put("msg", true);
        } else {
            json.put("data", error);
            json.put("msg", false);
        }
        return json.toJSONString();
    }




    /**
     * 属性过滤
     * @param object  需要过滤的对象 可以是实体 可以是List
     * @param notShowAttr   过滤的属性 有大小写之分
     * @param model  实体类
     * @param hidden 是否开启过滤
     * @param showNull 是否显示空值
     * @return
     */
    public static String  FilterAttr(Object object, String[] notShowAttr, Object model,boolean hidden,boolean showNull) {
        JSONObject json = new JSONObject();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        try {
            json.put("MSG","true");
            if(notShowAttr!=null&&notShowAttr.length>0&&hidden){
                for(String attrItem : notShowAttr){
                    filter.getExcludes().add(attrItem);
                }
                String data = JSONObject.toJSONString(object,filter);
                if(object instanceof ArrayList){
                    object = JSON.parseArray(data,model.getClass());
                }else{
                    object = JSON.parseObject(data,model.getClass());
                }
            }
            json.put("DATA", object);

        }catch (Exception e){
            json.put("MSG","false");
            json.put("DATA","失败" +e.getMessage()) ;
        }
        if(showNull){
            return JSONObject.toJSONString(json, SerializerFeature.WriteMapNullValue);
        }else {
            return json.toString();
        }
    }

    public static String  FilterAttrObj(Object object, String[] notShowAttr,boolean hidden,boolean showNull) {
        String data ="";
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        try {
            if(notShowAttr!=null&&notShowAttr.length>0&&hidden){
                for(String attrItem : notShowAttr){
                    filter.getExcludes().add(attrItem);
                }
               data = JSONObject.toJSONString(object,filter);
            }
        }catch (Exception e){
           return "";
        }

        if(showNull){
            return JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue);
        }else {
            return data;
        }
    }


    }
