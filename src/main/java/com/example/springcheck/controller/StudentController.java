package com.example.springcheck.controller;

import com.example.springcheck.dto.GetMyCourseDTO;
import com.example.springcheck.dto.GetMyCoursesDTO;
import com.example.springcheck.dto.SendApproveDTO;
import com.example.springcheck.service.AbsenceService;
import com.example.springcheck.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private AbsenceService absenceService;
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/sendApprove")
    public Long sendApprove(SendApproveDTO dto) {
        return absenceService.sendApprove(dto);
    }

    @GetMapping("/getMyCourses")
    public List<GetMyCoursesDTO> getMyCourses(@RequestParam String studentId) {
        return scheduleService.getMyCourses(studentId);
    }

    @GetMapping("/getMyCourse")
    public GetMyCourseDTO getMyCourse(@RequestParam String courseId) {
        return scheduleService.getMyCourse(courseId);
    }
}
