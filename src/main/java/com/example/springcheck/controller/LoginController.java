package com.example.springcheck.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springcheck.common.R;
import com.example.springcheck.common.ResultInfo;
import com.example.springcheck.dto.LoginDto;
import com.example.springcheck.token.TokenService;
import com.example.springcheck.entity.User;
import com.example.springcheck.service.UserService;
import com.example.springcheck.token.UserLoginToken;
import com.example.springcheck.utils.TokenUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;

//import org.apache.commons.codec.binary.Base64;
@RestController
@Slf4j
@RequestMapping("*")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    /*
    测试登录
     */
    @PostMapping("/login")
    public R<LoginDto> login(@RequestBody Map body, HttpServletResponse response) {
        String account = body.get("account").toString();
        String password = body.get("password").toString();
        log.info("hello");
        User user_r = userService.getById(account);
//       密码校验
//        if(!password.equals(user_r.getPassword())){
//            return R.login_error();
//        }
        String token = tokenService.getToken(user_r);
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
//        构造返回体
        LoginDto loginDto = new LoginDto();
        loginDto.setToken(token);
        LoginDto.user user = new LoginDto.user();
        user.setId(account);
        user.setName(user_r.getName());
        user.setType(user.getType());
        loginDto.setUser(user);
        return R.success(loginDto);
    }
}