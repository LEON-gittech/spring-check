package com.example.springcheck.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetMyCoursesDTO {
    private String courseId;
    private String courseName;
    private String courseTeacher;
    private String coursePlace;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
