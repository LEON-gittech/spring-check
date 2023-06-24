package com.example.springcheck.dto;

import lombok.Data;

@Data
public class LoginDto {
    @Data
    public static class user{
        private String id;
        private String name;
        private Integer type;
    }
    private user user;
    private String token;
}
