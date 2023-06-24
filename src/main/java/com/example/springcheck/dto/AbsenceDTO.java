package com.example.springcheck.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AbsenceDTO {
    private String name;
    private String id;
    private String week;
    private String courseName;
}
