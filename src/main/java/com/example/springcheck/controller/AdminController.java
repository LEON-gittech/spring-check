package com.example.springcheck.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springcheck.common.R;
import com.example.springcheck.dto.CourseDTO;
import com.example.springcheck.entity.*;
import com.example.springcheck.mapper.TeachesMapper;
import com.example.springcheck.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TakesService takesService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeachesService teachesService;

    @Autowired
    private TeachesMapper teachesMapper;

    // 五张表的插入操作
    @PostMapping("course")
    public R insertCourse(@RequestBody Course course){
        courseService.save(course);
        return R.success("课程加入成功");
    }

    @PostMapping("takes")
    public R insertTakes(@RequestBody Takes takes){
        takesService.save(takes);
        return R.success("插入成功");
    }

    @PostMapping("schedule")
    public R insertSchedule(@RequestBody Schedule schedule){
        scheduleService.save(schedule);
        return R.success("课表加入成功");
    }

    @PostMapping("user")
    public R insertUser(@RequestBody User user){
        userService.save(user);
        return R.success("用户加入成功");
    }

    @PostMapping("teaches")
    public R insertTeaches(@RequestBody Teaches teaches){
        teachesService.save(teaches);
        return R.success("插入成功");
    }

    // 给出老师id 返回老师管理的课程及其课程名
    @PostMapping("getTeacherClass")
    public R getTeacherClass(@RequestParam("teacher_id") String teacher_id){
        // 需要做课程的表连接
        List<CourseDTO> res = teachesMapper.getTeaClass(teacher_id);
        return R.success(res);
    }

    // 返回所有的老师
    @PostMapping("getAllTeacher")
    public R getAllTeacher(){
        // 返回所有的老师
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getType, 2);
        List<User> userList = userService.list(lambdaQueryWrapper);
        return R.success(userList);
    }

    // 返回所有的课程
    @PostMapping("getAllCourse")
    public R getAllClass(){
        List<Course> list = courseService.list();
        return R.success(list);
    }



}
