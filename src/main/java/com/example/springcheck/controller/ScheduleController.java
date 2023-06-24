package com.example.springcheck.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springcheck.common.R;
import com.example.springcheck.dto.CourseListDto;
import com.example.springcheck.entity.Schedule;
import com.example.springcheck.mapper.ScheduleMapper;
import com.example.springcheck.service.ScheduleService;
import com.example.springcheck.token.UserLoginToken;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@RestController
@RequestMapping()
@UserLoginToken
@Slf4j
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleMapper scheduleMapper;

    @GetMapping("/getCourseList")
    public R<CourseListDto> getCourseList(@RequestParam String id) {
        // 获取本周的开始日期和结束日期
        LocalDate now = LocalDate.now();
        LocalDate startOfWeek = now.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = now.with(java.time.DayOfWeek.SUNDAY);

        // 构建查询条件
        QueryWrapper<Schedule> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "SELECT s.id FROM schedule s JOIN takes t ON s.course_id = t.course_id WHERE t.student_id = '"
                        + id + "'")
                .between("start_time", LocalDateTime.of(startOfWeek, LocalTime.MIN), LocalDateTime.of(endOfWeek, LocalTime.MAX));

        // 执行查询
        List<Schedule> scheduleList = scheduleMapper.selectList(queryWrapper);
        var mapped = scheduleList.stream().map(m -> {
            var course = new CourseListDto.Course();
            course.setCourseId(m.getCourseId());
            course.setCourseName(m.getCourseTitle());
            course.setCoursePlace(m.getAddress());
            course.setStartTime(String.valueOf(m.getStartTime()));
            course.setEndTime(String.valueOf(m.getEndTime()));
            course.setScheduleId(String.valueOf(m.getId()));
            course.setCheck(m.getIsFinished());
            return course;
        }).collect(Collectors.toList());
        var result = new CourseListDto();
        result.setCourseList(mapped);
        return R.success(result);
    }
}

