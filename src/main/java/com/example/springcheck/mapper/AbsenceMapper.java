package com.example.springcheck.mapper;

import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.entity.Absence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
    List<MyApproves> getMyApproves(String studentId);

}
