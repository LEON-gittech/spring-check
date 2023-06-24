package com.example.springcheck.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcheck.dto.*;
import com.example.springcheck.entity.Absence;
import com.example.springcheck.entity.User;
import com.example.springcheck.mapper.AbsenceMapper;
import com.example.springcheck.service.AbsenceService;
import com.example.springcheck.service.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Service
public class AbsenceServiceImpl extends ServiceImpl<AbsenceMapper, Absence> implements AbsenceService {
    @Autowired
    private UserService userService;
    @Autowired
    private AbsenceMapper absenceMapper;

    @Override
    public GetApproveDTO getApprove(Long approveId) {
        Absence approve = lambdaQuery().eq(Absence::getId, approveId).one();
        User student = userService.getById(approve.getStudentId());
        return GetApproveDTO.builder().id(approveId).name(student.getName()).reason(approve.getReason()).isDecide(approve.getStatus() > 0).imgs(JSON.parseArray(approve.getImgs(), String.class)).build();
    }

    @Override
    public Boolean agree(Long approveId) {
        return lambdaUpdate().eq(Absence::getId, approveId).set(Absence::getStatus, 1).update();
    }

    @Override
    public Boolean reject(Long approveId) {
        return lambdaUpdate().eq(Absence::getId, approveId).set(Absence::getStatus, 2).update();
    }

    @Override
    public List<MyApproves> getMyApprovesById(String studentId) {
        return baseMapper.getMyApproves(studentId);
    }

    @Override
    public MyApprovePlus getMyApproveById(Long approveId) {
        MyApprovePlus myApprovePlus = baseMapper.getMyApprove(approveId);
        String img = myApprovePlus.getImg();
        List<String> res = JSON.parseArray(img, String.class);
        myApprovePlus.setImgs(res);
        return myApprovePlus;
    }

    @Override
    public GetAbsenceDTO getAbsence(String courseId) {
        var courseList = baseMapper.getAbsence(courseId);
        var courseName = courseList.get(0).getCourseName();
        return GetAbsenceDTO.builder().courseList(courseList).courseName(courseName).build();
    }

    @Override
    public Long sendApprove(SendApproveDTO dto) {
        var absence = new Absence();
        absence.setStudentId(dto.getStudentId());
        absence.setStatus(0);
        absence.setReason(dto.getReason());
        absence.setType(2);
        lambdaUpdate().update(absence);
        return absence.getId();
    }

    @Override
    public void saveMyData(Long scheduleId, Map<String, Object> data) {
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String stuId = entry.getKey();
            Integer statue = Integer.parseInt(entry.getValue().toString());
            if (statue == 0) {
                Absence absence = new Absence();
                absence.setStudentId(stuId);
                absence.setScheduleId(scheduleId);
                absence.setType(1);
                baseMapper.insert(absence);
            }

        }
    }

}
