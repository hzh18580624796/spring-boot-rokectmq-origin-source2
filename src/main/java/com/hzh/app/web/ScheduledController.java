package com.hzh.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

@RestController
public class ScheduledController {

    @Autowired
    private TaskScheduler taskScheduler;
    private Integer counter = 0;
    private Map<String, ScheduledFuture<?>> store = new HashMap<>();

    @Scheduled(cron = "*/1 * * * * ?")
    public void scheduled() {
        if (counter++ > 5) {
            return;
        }
        System.out.println("scheduled execute");
    }

    @GetMapping("/addSchedule")
    public void addSchedule(String key) {

        String cron = "1".equals(key) ? "*/1 * * * * ?" : "*/3 * * * * ?";

        CronTrigger cronTrigger = new CronTrigger(cron, TimeZone.getDefault());
        CronTask cronTask = new CronTask(() -> {
            System.out.println(key);
        }, cronTrigger);

        ScheduledFuture<?> future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());

        store.put(key, future);
    }

    @GetMapping("/deleteSchedule")
    public void deleteSchedule(String key) {

        ScheduledFuture<?> toRemove = store.remove(key);

        if (toRemove != null) {
            toRemove.cancel(true);
        }
        System.out.println(toRemove);
    }
}
