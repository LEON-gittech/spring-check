package com.example.springcheck.entity;

import java.time.LocalDateTime;
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
public class Schedule implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 日程表id
     */
      private Long id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 课程名称
     */
    private String courseTitle;

    /**
     * 上课时间
     */
    private LocalDateTime startTime;

    /**
     * 下课时间
     */
    private LocalDateTime endTime;

    /**
     * 上课地点
     */
    private String address;

    /**
     * 是否已经签到完成
     */
    private Integer isFinished;


}
