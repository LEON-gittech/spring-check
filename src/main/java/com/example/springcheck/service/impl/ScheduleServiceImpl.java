package com.example.springcheck.service.impl;

import com.example.springcheck.dto.GetCoursesDTO;
import com.example.springcheck.dto.GetMyCourseDTO;
import com.example.springcheck.dto.GetMyCoursesDTO;
import com.example.springcheck.entity.Course;
import com.example.springcheck.entity.Schedule;
import com.example.springcheck.entity.Teaches;
import com.example.springcheck.mapper.ScheduleMapper;
import com.example.springcheck.service.ScheduleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcheck.service.TeachesService;
import com.example.springcheck.service.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Service
public class ScheduleServiceImpl extends ServiceImpl<ScheduleMapper, Schedule> implements ScheduleService {

    @Autowired
    private TeachesService teachesService;
    @Autowired
    private UserService userService;

    @Override
    public List<GetCoursesDTO> getCourses(String teacherId) {
        // 获取本周的开始日期和结束日期
//        LocalDate now = LocalDate.now();
//        LocalDateTime startOfWeek = LocalDateTime.of(now.with(java.time.DayOfWeek.MONDAY), LocalTime.MIN);
//        LocalDateTime endOfWeek = LocalDateTime.of(now.with(java.time.DayOfWeek.SUNDAY), LocalTime.MAX);
        var result = baseMapper.getCourses(teacherId);
        result.forEach(r -> r.setCourseTime(r.getStartTime() + " - " + r.getEndTime()));
        return result;
    }

    @Override
    public List<GetMyCoursesDTO> getMyCourses(String studentId) {
        // 获取本周的开始日期和结束日期
//        LocalDate now = LocalDate.now();
//        LocalDateTime startOfWeek = LocalDateTime.of(now.with(java.time.DayOfWeek.MONDAY), LocalTime.MIN);
//        LocalDateTime endOfWeek = LocalDateTime.of(now.with(java.time.DayOfWeek.SUNDAY), LocalTime.MAX);
        return baseMapper.getMyCourses(studentId);
    }

    @Override
    public GetMyCourseDTO getMyCourse(String courseId) {
        var s = lambdaQuery().eq(Schedule::getCourseId, courseId)
                .one();
        return GetMyCourseDTO.builder()
                .courseId(s.getCourseId())
                .coursePlace(s.getAddress())
                .courseName(s.getCourseTitle())
                .startTime(s.getStartTime())
                .endTime(s.getEndTime())
                .build();
    }
}
