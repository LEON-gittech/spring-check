package com.example.springcheck.mapper;

import com.example.springcheck.dto.MyApprovePlus;
import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.entity.Absence;
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
public interface AbsenceMapper extends BaseMapper<Absence> {
    String querySql = "select aa.permit as status, ss.course_title as courseName, MONTH(ss.start_time) as approveMonth, Day(ss.start_time) as approveDay, aa.id as approveId from absence aa LEFT JOIN `schedule` ss on aa.schedule_id = ss.id where aa.student_id= ${ew}";
    @Select(querySql)
    List<MyApproves> getMyApproves(@Param("ew") String studentId);

    String querySql1 = "select aa.permit as status, ss.course_title as courseName, MONTH(ss.start_time) as approveMonth, Day(ss.start_time) as approveDay, aa.id as approveId, aa.desc as reason, aa.imgs as img from absence aa LEFT JOIN `schedule` ss on aa.schedule_id = ss.id where aa.id= ${ew1}";
    @Select(querySql1)
    MyApprovePlus getMyApprove(@Param("ew1") Long approveId);

}
