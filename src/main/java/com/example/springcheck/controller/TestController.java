package com.example.springcheck.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springcheck.Scheduler.MyScheduler;
import com.example.springcheck.common.R;
import com.example.springcheck.dto.*;
import com.example.springcheck.entity.*;
import com.example.springcheck.service.*;
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
    private TeachesService teachesService;

    @Autowired
    private TakesService takesService;

    @Autowired
    private UserService userService;
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
    public R Notify(Long scheduleId){
        // 课程信息、未签到名单、comment
        Notify notify = new Notify();
        Schedule schedule = scheduleService.getById(scheduleId);
        notify.setCourseId(schedule.getCourseId());
        notify.setCourseName(schedule.getCourseTitle());
        notify.setStartTime(schedule.getStartTime());
        notify.setEndTime(schedule.getEndTime());
        notify.setCoursePlace(schedule.getAddress());

        // 查询老师信息
        LambdaQueryWrapper<Teaches> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Teaches::getCourseId,schedule.getCourseId());
        String teaId = teachesService.getOne(lambdaQueryWrapper).getTeacherId();
        User user = userService.getById(teaId);
        notify.setCourseTeacher(user.getName());

        // 获取未签到的人
        List<UserDto> list = new ArrayList<>();
        Map<String, Object> data = redisTemplate.opsForHash().entries(String.valueOf(scheduleId));

        for(Map.Entry<String, Object> entry: data.entrySet()){
            String stuId = entry.getKey();
            Integer statue = Integer.parseInt(entry.getValue().toString());
            if(statue == 0){
                UserDto userDto = new UserDto();
                userDto.setId(stuId);
                userDto.setName(userService.getById(stuId).getName());
                list.add(userDto);
            }
        }
        notify.setUnSignList(list);
        String comment = "";
        for(UserDto userDto:list){
            comment += userDto.getName();
            comment += "、";
        }
        comment = comment.substring(0, comment.length() - 1);
        comment+="未签到，请及时通知";
        notify.setComment(comment);
        return R.success(notify);
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
            hashOperations.put(String.valueOf(scheduleId), takes.getStudentId(), "0");
        }
        // 加入redis 根据scheduleId构建两个 一个是学号 一个是状态
        long delay = 10 * 60 * 1000;
        LocalDateTime scheduledTime = LocalDateTime.now().plusMinutes(10);
        myScheduler.saveAbsence(delay, scheduleId);

        return R.success("");
    }

    @PostMapping("getCheck")
    public R getCheck(Long scheduleId){
        Integer signNum = 0;
        Integer totalNum = 0;
        List<UserDto> signList = new ArrayList<>();
        List<UserDto> unSignList = new ArrayList<>();
        Map<String, Object> data = redisTemplate.opsForHash().entries(String.valueOf(scheduleId));

        for(Map.Entry<String, Object> entry: data.entrySet()){
            String stuId = entry.getKey();
            Integer statue = Integer.parseInt(entry.getValue().toString());
            UserDto userDto = new UserDto();
            userDto.setId(stuId);
            userDto.setName(userService.getById(stuId).getName());
            if(statue == 0){
                unSignList.add(userDto);
            }else{
                signList.add(userDto);
                signNum++;
            }
            totalNum++;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("signNum", signNum);
        res.put("totalNum", totalNum);
        res.put("signList", signList);
        res.put("unSignList", unSignList);
        return R.success(res);
    }


}
