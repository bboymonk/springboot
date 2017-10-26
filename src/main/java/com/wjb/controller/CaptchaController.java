package com.wjb.controller;

import com.wjb.base.BaseController;
import com.wjb.util.CreateImageCode;
import com.wjb.util.StaticFinalParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Administrator on 2017/10/26.
 */
@Controller
public class CaptchaController extends BaseController{
    @GetMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void validateImages(HttpServletResponse response, HttpSession session) throws IOException {
        Random random = new Random();
        CreateImageCode code = new CreateImageCode(160,40,4,10,random.nextInt(3)%3 + 1);
        BufferedImage image = code.getBuffImg();
        String validateCode = code.getCode();
        session.setAttribute(StaticFinalParam.ADMIN_VALIDATE_CODE_KEY, validateCode);
        ImageIO.write(image, "png", response.getOutputStream());
    }

}
