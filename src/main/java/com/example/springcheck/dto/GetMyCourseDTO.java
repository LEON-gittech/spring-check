package com.example.springcheck.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetMyCourseDTO {
    private String courseId;
    private String courseName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String coursePlace;
}
