package com.example.springcheck.controller;

import com.example.springcheck.common.R;
import com.example.springcheck.dto.ApprovesPlus;
import com.example.springcheck.dto.MyApprovePlus;
import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/apitest")
public class TestController {
    @Autowired
    private AbsenceService absenceService;
    @PostMapping("getMyApproves")
    public R getMyApproves(String studentId){
//        System.out.println(studentId);
        List<MyApproves> list = absenceService.getMyApprovesById(studentId);
        return R.success(list);
    }

    @PostMapping("getMyApprove")
    public R getMyApprove(Long approveId){
        MyApprovePlus myApproves = absenceService.getMyApproveById(approveId);
        return R.success(myApproves);
    }

    @PostMapping("getApproves")
    public R getApproves(String courseId){
        List<ApprovesPlus> approvesPlus = absenceService.getApprovesById(courseId);
        Map<String, Object> res = new HashMap<>();
        res.put("approves", approvesPlus);
        return R.success(res);
    }


}
