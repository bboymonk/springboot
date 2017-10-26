package com.wjb.util;

import org.springframework.util.StringUtils;

/**
*@Author:wjb
*@params:
*@Date:11:41 2017/10/26
*/
public class StaticFinalParam {

    public static final int pageShowNumS = 10;

    public static final String ADMIN_VALIDATE_CODE_KEY = "ADMIN_VALIDATE_CODE_KEY";

    public static final String FRONT_VALIDATE_CODE_KEY = "FRONT_VALIDATE_CODE_KEY";

    public static final Integer[] PERIODARR = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};

    public static  enum TaskEnum{
        WORKING,STOP
    }


    public static String getNotNullString(String param){
        return StringUtils.isEmpty(param)?null:param;
    }

    public static String getNotNullStringReplace(String param,String param1){
        return StringUtils.isEmpty(param)?param1:param;
    }

    public static Integer getNotNullInteger(Integer param,Integer param1){
        return param==null?param1:param;
    }


}
