package com.example.springcheck.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springcheck.dto.GetAbsenceDTO;
import com.example.springcheck.dto.GetApproveDTO;
import com.example.springcheck.dto.GetCoursesDTO;
import com.example.springcheck.entity.Absence;
import com.example.springcheck.service.AbsenceService;
import com.example.springcheck.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/getApprove")
    public GetApproveDTO getApprove(@RequestParam Long approveId) {
        return absenceService.getApprove(approveId);
    }

    @PostMapping("/agree")
    public Boolean agree(Long approveId) {
        return absenceService.agree(approveId);
    }

    @PostMapping("/reject")
    public Boolean reject(Long approveId) {
        return absenceService.reject(approveId);
    }

    @GetMapping("/getCourses")
    public List<GetCoursesDTO> getCourses(@RequestParam String teacherId) {
        return scheduleService.getCourses(teacherId);
    }

    @GetMapping("/getAbsence")
    public GetAbsenceDTO getAbsence(@RequestParam String courseId) {
        return absenceService.getAbsence(courseId);
    }
}
