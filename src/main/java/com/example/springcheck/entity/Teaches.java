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
public class Teaches implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 排课id
     */
      private String courseId;

    /**
     * 任课老师教职工号
     */
    private String teacherId;


}
