package com.example.springcheck.controller;

import com.example.springcheck.common.R;
import com.example.springcheck.dto.MyApproves;
import com.example.springcheck.service.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apitest")
public class TestController {
    @Autowired
    private AbsenceService absenceService;
    @GetMapping("getMyApproves")
    public R getMyApproves(String studentId){
        System.out.println(studentId);
        List<MyApproves> list = absenceService.getMyApprovesById(studentId);
        return R.success(list);
    }

}
