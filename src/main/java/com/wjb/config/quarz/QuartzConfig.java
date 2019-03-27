package com.wjb.config.quarz;

import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by wjb on 2019/3/27.
 */
@Configuration
public class QuartzConfig {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobConfig.class);

    /**
     * 调度器工厂Bean
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("myFirstJobTrigger") Trigger myFirstJobTrigger,
                                                 @Qualifier("mySecondJobTrigger") Trigger mySecondJobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
        } catch (IOException e) {
            logger.error("加载quartz.properties失败", e);
        }
        bean.setQuartzProperties(p);
        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        // 注册触发器
        bean.setTriggers(myFirstJobTrigger, mySecondJobTrigger);
        return bean;
    }

    /**
     * 调度器工厂Bean,注册所有的trigger
     */
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(Trigger... trigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        Properties p = new Properties();
        try {
            p.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
        } catch (IOException e) {
            logger.error("加载quartz.properties失败", e);
        }
        bean.setQuartzProperties(p);
        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        // 注册触发器
        bean.setTriggers(trigger);
        return bean;
    }

}
