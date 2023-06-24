package com.example.springcheck.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendApproveDTO {
    private String reason;
    private Long scheduleId;
    private String studentId;
}
