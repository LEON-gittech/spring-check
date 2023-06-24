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
public class Course{

    private static final long serialVersionUID=1L;

    /**
     * 课程id
     */
      private String id;

    /**
     * 课程名称
     */
    private String title;

    /**
     * 课程简介
     */
    private String content;


}
