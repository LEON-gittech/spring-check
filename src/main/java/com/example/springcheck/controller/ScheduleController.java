package com.example.springcheck.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.springcheck.service.ScheduleService;
import com.example.springcheck.token.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@RestController
@RequestMapping()
@UserLoginToken
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;
    @GetMapping("/getCourseList")
    public R<String> getCourseList(){
        return null;
    }
}

