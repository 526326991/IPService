package com.example.demo.schedule;

import com.example.demo.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * 定时任务
 */
@Configuration
@EnableScheduling   //开启定时任务
@PropertySource(value = "classpath:info.properties")
public class ScheduleTask {

    @Autowired
    private SendEmail email;


    @Scheduled(cron = "${e.cron}")
    private void configureTasks() {
        email.send();
    }
}