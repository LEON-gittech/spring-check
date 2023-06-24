package com.example.springcheck.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springcheck.dto.GetApproveDTO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.example.springcheck.dto.MyApprovePlus;
import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.entity.Absence;
import com.example.springcheck.mapper.AbsenceMapper;
import com.example.springcheck.service.AbsenceService;
import com.example.springcheck.service.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public GetApproveDTO getApprove(Long approveId) {
        var approve = getById(approveId);
        var student = userService.getById(approve.getStudentId());
        return GetApproveDTO.builder().id(approveId).name(student.getName()).reason(approve.getDesc()).isDecide(approve.getPermit() > 0).imgs(JSON.parseArray(approve.getImgs(), String.class)).build();
    }

    @Override
    public Boolean agree(Long approveId) {
        return lambdaUpdate().eq(Absence::getId, approveId).set(Absence::getPermit, 1).update();
    }

    @Override
    public Boolean reject(Long approveId) {
        return lambdaUpdate().eq(Absence::getId, approveId).set(Absence::getPermit, 2).update();
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
    public List<ApprovesPlus> getApprovesById(String courseId) {
        List<ApprovesPlus> res = baseMapper.getApprovesPlus(courseId);
        System.out.println(res.toString());
        return res;

    }
}
