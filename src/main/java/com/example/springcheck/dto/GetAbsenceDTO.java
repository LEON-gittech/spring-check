package com.example.springcheck.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetAbsenceDTO {
    private String courseName;
    private List<AbsenceDTO> courseList;
}
