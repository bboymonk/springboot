package com.wjb.util.scheduled;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wjb on 2017/10/26.
 */
public class ScheduledThreadPoolExecutorDemo {
    private static class myTask implements Runnable{

        @Override
        public void run() {
            System.out.println("timer test");
        }
    }

    /**
     * 5秒后执行，执行完任务等2秒再执行，一直循环下去
     * ScheduledThreadPoolExecutor还有很多方法，可以设置时间周期
     * @param args
     */
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5);
        executor.scheduleWithFixedDelay(new myTask(),5,2, TimeUnit.SECONDS);
    }
}
