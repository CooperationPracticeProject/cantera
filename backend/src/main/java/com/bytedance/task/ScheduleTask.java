package com.bytedance.task;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author: 繁星_逐梦
 * @date: 2025/1/16 下午2:50
 * @description: 定时任务
 */

@Component
@EnableScheduling
public class ScheduleTask {

//  @Scheduled (cron = "*/60 * * * * ?")
//  public void showTime () {
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    System.out.println("当前时间：" + sdf.format(System.currentTimeMillis()));
//  }

}
