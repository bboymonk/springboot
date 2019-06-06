package com.wjb.config.web;

import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
*@Author:wjb
*@params:
*@Date:14:33 2017/10/19
*/
@Configuration
@MapperScan(basePackages = "com.wjb.*.mapper")
public class MybatisMapperScannerConfig {
    private final static Logger logger = Logger.getLogger(MybatisMapperScannerConfig.class);

    @Bean
    public PageHelper pageHelper(){
        logger.info("------pageHelper");
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
