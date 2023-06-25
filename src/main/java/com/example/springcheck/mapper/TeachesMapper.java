package com.example.springcheck.mapper;

import com.example.springcheck.dto.CourseDTO;
import com.example.springcheck.entity.Teaches;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Mapper
public interface TeachesMapper extends BaseMapper<Teaches> {
    String sql = "SELECT course_id as courseId, title from teaches LEFT JOIN course ON course.id = teaches.course_id where teacher_id=${ew}";
    @Select(sql)
    List<CourseDTO> getTeaClass(@Param("ew") String teacher_id);

}
