package com.example.springcheck.mapper;

import com.example.springcheck.dto.GetCoursesDTO;
import com.example.springcheck.dto.GetMyCoursesDTO;
import com.example.springcheck.entity.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {
    @Select("select t.course_id as courseId, course_title as courseName, address as coursePlace, is_finished as isCheck, start_time as startTime, end_time as endTime from schedule inner join teaches t on schedule.course_id = t.course_id " +
            "inner join `check`.user on teacher_id = user.id where teacher_id=#{teacherId} and start_time >= #{monday} and start_time < #{nextMonday};")
    List<GetCoursesDTO> getCoursesWithRange(@Param(value = "teacherId") String teacherId, @Param(value = "monday") LocalDateTime monday, @Param(value = "nextMonday") LocalDateTime nextMonday);

    @Select("select t.course_id as courseId, course_title as courseName, address as coursePlace, is_finished as isCheck, start_time as startTime, end_time as endTime from schedule inner join teaches t on schedule.course_id = t.course_id " +
            "inner join `check`.user on teacher_id = user.id where teacher_id = #{teacherId}")
    List<GetCoursesDTO> getCourses(@Param(value = "teacherId") String teacherId);

    @Select("select t.course_id as courseId, course_title as courseName, address as coursePlace, is_finished as isCheck, start_time as startTime, end_time as endTime from schedule inner join takes t on schedule.course_id = t.course_id " +
            "inner join `check`.user on student_id= user.id where student_id = #{studentId} and start_time >= #{monday} and start_time < #{nextMonday};")
    List<GetMyCoursesDTO> getMyCoursesWithRange(@Param("studentId") String studentId, @Param("monday") LocalDateTime monday, @Param("nextMonday") LocalDateTime nextMonday);

    @Select("select t.course_id as courseId, course_title as courseName, address as coursePlace, is_finished as isCheck, start_time as startTime, end_time as endTime from schedule " +
            "inner join takes t on schedule.course_id = t.course_id " +
            "inner join user on t.student_id = user.id where student_id=#{courseId}")
    List<GetMyCoursesDTO> getMyCourses(@Param(value = "courseId") String courseId);

    @Select("select * from schedule inner join takes t on schedule.course_id = t.course_id " + "where start_time >= #{monday} and start_time < #{nextMonday}")
    List<GetCoursesDTO> getCourseList(@Param(value = "id") String id, @Param(value = "monday") LocalDateTime monday, @Param(value = "nextMonday") LocalDateTime nextMonday);
}
