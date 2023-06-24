package com.example.springcheck.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springcheck.entity.User;
import com.example.springcheck.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserUtils {
    //根据传入的User信息匹配数据库中的User
    @Autowired
    @Lazy
    private UserService userService;
    public User getUser(User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, user.getId());
        queryWrapper.eq(User::getType,user.getType());
        User user_r = userService.getOne(queryWrapper);
        return user_r;
    }
}
