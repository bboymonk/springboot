package com.wjb.config.shiro;

import com.wjb.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2017/7/28.
 */
public class ShiroKit {

    public static String md5(String password,String salt) {
        String p = new Md5Hash(password, salt,2).toHex();
        return p;
    }

    public static Object getShiroAdmin(){
        Subject subject = SecurityUtils.getSubject();
        return (User)subject.getPrincipal();
    }

    public static void removeShiroSession(){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().stop();
    }

    public static void main(String[] args) {
        System.out.println(md5("201314","wjb"));
    }



}
