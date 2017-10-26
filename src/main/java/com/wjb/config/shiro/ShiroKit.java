package com.wjb.config.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ShiroKit {

    public static String md5(String password,String salt) {
        String newPassword = new SimpleHash("MD5",password,salt,2).toString();
        return newPassword;
    }

    public static Object getShiroAdmin(){
        Subject subject = SecurityUtils.getSubject();
        return   subject.getPrincipal();
    }

    public static void removeShiroSession(){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().stop();
    }

}
