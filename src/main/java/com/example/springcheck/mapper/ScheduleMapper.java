package com.example.springcheck.mapper;

import com.example.springcheck.entity.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

}