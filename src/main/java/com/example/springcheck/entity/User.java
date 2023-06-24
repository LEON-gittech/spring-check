package com.example.springcheck.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 学号或教职工号，同时作为用户名
     */
      private String id;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 1——学生
2——任课老师
3——教务老师
4——辅导员
     */
    private Integer type;

    /**
     * 小程序id
     */
    private String openId;

    /**
     * 学生所关联的辅导员id
     */
    private String instructorId;


}
