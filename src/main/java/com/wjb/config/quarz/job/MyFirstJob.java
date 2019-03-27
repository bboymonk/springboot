package com.wjb.config.quarz.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * Created by wjb on 2019/3/27.
 */
@Component
@EnableScheduling
@DisallowConcurrentExecution //保证上一次任务执行完再执行下一次任务
public class MyFirstJob implements Job{

    private static final Logger logger = LoggerFactory.getLogger(MyFirstJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        this.myFirstJobDemo();
    }

    public void myFirstJobDemo(){
        System.out.println("第一个任务测试方法");
    }

}
