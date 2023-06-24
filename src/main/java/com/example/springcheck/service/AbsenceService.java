package com.example.springcheck.service;

import com.example.springcheck.dto.ApprovesPlus;
import com.example.springcheck.dto.MyApprovePlus;
import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.entity.Absence;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
public interface AbsenceService extends IService<Absence> {
    List<MyApproves> getMyApprovesById(String studentId);

    MyApprovePlus getMyApproveById(Long approveId);

    List<ApprovesPlus> getApprovesById(String courseId);
}
