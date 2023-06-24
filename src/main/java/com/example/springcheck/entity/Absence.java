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
public class Absence implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 缺勤条目id
     */
    private Long id;

    /**
     * 日程条目id
     */
    private Long scheduleId;

    /**
     * 学生学号
     */
    private String studentId;

    /**
     * 1——旷课
     * 2——请假
     */
    private Integer type;

    /**
     * 请假事由
     */
    private String desc;

    /**
     * 是否批准
     * 0 代表未审批，1 代表通过，2 代表拒绝
     */
    private Integer permit;

    private String imgs;


}
