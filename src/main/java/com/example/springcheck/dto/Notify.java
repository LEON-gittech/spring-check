package com.example.springcheck.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Notify {
    private String courseId;
    private String courseName;
    private String courseTeacher;
    private Integer week;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String coursePlace;
    private List<UserDto> unSignList;
    private String comment;
}
