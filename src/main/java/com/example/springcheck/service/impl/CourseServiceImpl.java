package com.example.springcheck.service.impl;

import com.example.springcheck.entity.Course;
import com.example.springcheck.mapper.CourseMapper;
import com.example.springcheck.service.CourseService;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
