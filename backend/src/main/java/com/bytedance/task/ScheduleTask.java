package com.bytedance.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/16 下午2:50
 * @description: 定时任务
 */

@Component
@EnableScheduling
public class ScheduleTask {

    @Scheduled(cron ="*/30 * * * * ?")
    public void sayHello() {
        System.out.println("hello");
    }


}
