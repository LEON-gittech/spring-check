package com.example.springcheck.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetApproveDTO {
    private String name;
    private Long id;
    private String reason;
    private List<String> imgs;
    private Boolean isDecide;
}
