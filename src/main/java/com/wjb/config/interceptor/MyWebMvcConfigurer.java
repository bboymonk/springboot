package com.wjb.config.interceptor;

import com.wjb.config.countPvUv.PvUvCount;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/10/27.
 */
@Configuration
public class MyWebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new PvUvCount());
        registration.addPathPatterns("/**");
//        registration.excludePathPatterns("/css/**");
//        registration.excludePathPatterns("/font/**");
//        registration.excludePathPatterns("/img/**");
//        registration.excludePathPatterns("/js/**");
//        registration.excludePathPatterns("/captcha/**");
        super.addInterceptors(registry);
    }
}
