package com.example.springcheck.service;

import com.example.springcheck.dto.GetApproveDTO;
import com.example.springcheck.entity.Absence;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
