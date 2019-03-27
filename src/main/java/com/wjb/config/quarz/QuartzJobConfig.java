package com.wjb.config.quarz;

import com.wjb.config.quarz.job.MyFirstJob;
import com.wjb.config.quarz.job.MySecondJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;

/**
 * Created by wjb on 2019/3/27.
 */
@Configuration
public class QuartzJobConfig {

    private static final Logger logger = LoggerFactory.getLogger(QuartzJobConfig.class);

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "myFirstJobBean")
    public MethodInvokingJobDetailFactoryBean myFirstExerciseJobBean(MyFirstJob myFirstJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false); // 是否并发
        jobDetail.setName("general-myFirstJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setTargetObject(myFirstJob); // 被执行的对象
        jobDetail.setTargetMethod("myFirstJobDemo"); // 被执行的方法
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "myFirstJobTrigger")
    public CronTriggerFactoryBean myFirstExerciseJobTrigger(@Qualifier("myFirstJobBean") MethodInvokingJobDetailFactoryBean myFirstJobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(myFirstJobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-myFirstJobTrigger");
        return tigger;
    }

    /**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "mySecondJobBean")
    public MethodInvokingJobDetailFactoryBean mySecondExerciseJobBean(MySecondJob mySecondJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false); // 是否并发
        jobDetail.setName("general-mySecondJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setTargetObject(mySecondJob); // 被执行的对象
        jobDetail.setTargetMethod("mySecondJobDemo"); // 被执行的方法
        return jobDetail;
    }

    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "mySecondJobTrigger")
    public CronTriggerFactoryBean mySecondExerciseJobTrigger(@Qualifier("mySecondJobBean") MethodInvokingJobDetailFactoryBean mySecondJobDetailFactoryBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(mySecondJobDetailFactoryBean.getObject());
        tigger.setCronExpression("0/20 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-mySecondJobTrigger");
        return tigger;
    }



}
