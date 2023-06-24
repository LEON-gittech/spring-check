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
public class Takes{

    private static final long serialVersionUID=1L;

    /**
     * 排课id
     */
      private String courseId;

    /**
     * 学生学号
     */
    private String studentId;


}
