package com.example.springcheck.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GetCoursesDTO {
    private String courseId;
    private String courseName;
    private String courseTime;
    private String coursePlace;
    private Boolean isCheck;
}
