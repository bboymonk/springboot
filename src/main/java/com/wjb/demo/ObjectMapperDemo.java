package com.wjb.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjb.model.User;

/**
 * Created by Administrator on 2017/11/4.
 */
public class ObjectMapperDemo {
    public static void main(String[] args) throws JsonProcessingException {
        User user = new User();
        user.setName("wjb");
        user.setPassword("123456");
        user.setMobile("15905452112");
        user.setEmail("132@qq.com");
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(user);
        System.out.println(s);


    }
}
