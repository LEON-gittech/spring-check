package com.example.springcheck.service.impl;

import com.example.springcheck.entity.User;
import com.example.springcheck.mapper.UserMapper;
import com.example.springcheck.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
