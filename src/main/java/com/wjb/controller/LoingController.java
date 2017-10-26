package com.wjb.controller;

import com.wjb.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/10/26.
 */
@Controller
@RequestMapping("admin")
public class LoingController extends BaseController {
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("toLogin")
    public String toLogin(){


        return null;
    }

}
