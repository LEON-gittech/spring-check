package com.example.springcheck.Scheduler;

import com.example.springcheck.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Component
public class MyScheduler {
    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private RedisTemplate redisTemplate;

    // 十分钟
    @Scheduled(fixedDelay = 10 * 60 * 1000)
    public void saveAbsence(long delay, Long scheduleId){
        HashOperations hashOperations = redisTemplate.opsForHash();
        // 先查出内容
        Map<String, Object> data = hashOperations.entries(scheduleId);
        taskScheduler.schedule(()->{
            absenceService.saveMyData(scheduleId, data);
        }, Instant.now().plusMillis(delay));

    }



}
