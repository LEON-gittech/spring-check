package com.example.springcheck.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 * <p>
 *
 * </p>
 *
 * @author fvres
 * @since 2023-06-24
 */
@Data
public class Absence{

    private static final long serialVersionUID = 1L;

    /**
     * 缺勤条目id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    private String reason;

    /**
     * 是否批准
     * 0 代表未审批，1 代表通过，2 代表拒绝
     */
    private Integer status;

    private String imgs;


}
