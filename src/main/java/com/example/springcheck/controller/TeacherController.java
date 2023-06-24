package com.example.springcheck.controller;

import com.example.springcheck.dto.GetAbsenceDTO;
import com.example.springcheck.dto.GetApproveDTO;
import com.example.springcheck.dto.GetCoursesDTO;
import com.example.springcheck.service.AbsenceService;
import com.example.springcheck.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/getApprove")
    public GetApproveDTO getApprove(String courseId, Long approveId) {
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
    public List<GetCoursesDTO> getCourses(String teacherId) {
        return scheduleService.GetCourses(teacherId);
    }

    @GetMapping("/getAbsence")
    public GetAbsenceDTO getAbsence(String courseId) {
        return absenceService.getAbsence(courseId);
    }

}
