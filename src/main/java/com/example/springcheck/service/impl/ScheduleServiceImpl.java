package com.example.springcheck.service.impl;

import com.example.springcheck.dto.GetCoursesDTO;
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
    public List<GetCoursesDTO> GetCourses(String teacherId) {
        var sql = "select * from schedule inner join takes t on " +
                "schedule.course_id = t.course_id " + "where start_time >= ${monday} and start_time < ${nextMonday}";
        var courseIds = teachesService.lambdaQuery().eq(Teaches::getTeacherId, teacherId).list();
        var courses = lambdaQuery().in(Schedule::getCourseId, courseIds).list();

    }
}
