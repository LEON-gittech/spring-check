package com.example.springcheck.service;

import com.example.springcheck.dto.*;
import com.example.springcheck.entity.Absence;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
public interface AbsenceService extends IService<Absence> {
    GetApproveDTO getApprove(Long approveId);

    Boolean agree(Long approveId);

    Boolean reject(Long approveId);

    List<MyApproves> getMyApprovesById(String studentId);

    MyApprovePlus getMyApproveById(Long approveId);

    GetAbsenceDTO getAbsence(String courseId);

    Long sendApprove(SendApproveDTO dto);

    void saveMyData(Long scheduleId, Map<String, Object> data);

    List<ApprovesPlus> getApprovesById(String courseId);

}
