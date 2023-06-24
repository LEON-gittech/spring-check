package com.example.springcheck.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// /getCourseList
@Data
public class CourseListDto {
    @Data
    public static class Course{
        private String courseId;
        private String scheduleId;
        private String courseName;
        private String startTime;
        private String endTime;
        private String coursePlace;
        private boolean check;
    }
    List<Course> courseList = new ArrayList<>();
}
