package com.example.springcheck.service.impl;

import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.entity.Absence;
import com.example.springcheck.mapper.AbsenceMapper;
import com.example.springcheck.service.AbsenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcheck.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Service
public class AbsenceServiceImpl extends ServiceImpl<AbsenceMapper, Absence> implements AbsenceService {
    @Autowired
    private ScheduleService scheduleService;
    @Override
    public List<MyApproves> getMyApprovesById(String studentId) {
        List<MyApproves> list = baseMapper.getMyApproves(studentId);
        return list;
    }
}
