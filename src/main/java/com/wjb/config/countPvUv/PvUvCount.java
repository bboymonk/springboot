package com.wjb.config.countPvUv;

import com.wjb.util.common.Common;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/10/27.
 */
@Component
public class PvUvCount implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Cookie[] cookies = request.getCookies();
//        for (Cookie c : cookies) {
//            if(c.getValue().equals("PPT")){
//                System.out.println("................"+c.getMaxAge());
//                c.setMaxAge(Common.COOKIE_TIME);
//                response.addCookie(c);
//            }
//            System.out.println(c.getName()+"==========="+c.getValue()+"============="+c.getMaxAge());
//        }

//        String ip = Common.getIp(request);
//        if (!StringUtils.isEmpty(ip)) {
//            System.out.println(ip);
//            return true;
//        }
//        return false;
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle....");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion...");
    }
}
