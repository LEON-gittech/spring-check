package com.example.springcheck.dto;

import lombok.Data;

import java.util.List;

@Data
public class MyApprovePlus extends MyApproves{
    private String reason;
    private List<String> imgs;
    private String img;
}
