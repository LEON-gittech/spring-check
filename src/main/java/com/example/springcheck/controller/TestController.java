package com.example.springcheck.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springcheck.Scheduler.MyScheduler;
import com.example.springcheck.common.R;
import com.example.springcheck.dto.ApprovesPlus;
import com.example.springcheck.dto.MyApprovePlus;
import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.entity.Schedule;
import com.example.springcheck.entity.Takes;
import com.example.springcheck.service.AbsenceService;
import com.example.springcheck.service.ScheduleService;
import com.example.springcheck.service.TakesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/apitest")
public class TestController {
    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TakesService takesService;
    @PostMapping("getMyApproves")
    public R getMyApproves(String studentId){
//        System.out.println(studentId);
        List<MyApproves> list = absenceService.getMyApprovesById(studentId);
        return R.success(list);
    }

    @PostMapping("getMyApprove")
    public R getMyApprove(Long approveId){
        MyApprovePlus myApproves = absenceService.getMyApproveById(approveId);
        return R.success(myApproves);
    }

    @PostMapping("getApproves")
    public R getApproves(String courseId){
        List<ApprovesPlus> approvesPlus = absenceService.getApprovesById(courseId);
        Map<String, Object> res = new HashMap<>();
        res.put("approves", approvesPlus);
        return R.success(res);
    }

    @PostMapping("notify")
    public R Notify(String scheduleId){
        // 课程信息、未签到名单、comment
        return null;
    }
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MyScheduler myScheduler;

    @PostMapping("startCheck")
    public R startCheck(Long scheduleId){
        // 在redis中放入内容
        // 获取courseId
        Schedule schedule = scheduleService.getById(scheduleId);
        String courseId = schedule.getCourseId();
        // 先查出所有学生的id
        List<String> ids = new ArrayList<>();
        List<String> status = new ArrayList<>();
        LambdaQueryWrapper<Takes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Takes::getCourseId, courseId);
        List<Takes> takesList = takesService.list(lambdaQueryWrapper);
        HashOperations hashOperations = redisTemplate.opsForHash();
        for(Takes takes: takesList){
            System.out.println(takes.getStudentId());
            hashOperations.put(scheduleId, takes.getStudentId(), "0");
        }
        // 加入redis 根据scheduleId构建两个 一个是学号 一个是状态
        long delay = 10 * 60 * 1000;
        LocalDateTime scheduledTime = LocalDateTime.now().plusMinutes(10);
        myScheduler.saveAbsence(delay, scheduleId);

        return R.success("");

    }




}
