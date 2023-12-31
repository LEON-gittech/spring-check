package com.example.springcheck.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyApproves implements Serializable {

    private Integer status;

    private String courseName;

    private Integer approveMonth;

    private Integer approveDay;

    private Long approveId;
}
