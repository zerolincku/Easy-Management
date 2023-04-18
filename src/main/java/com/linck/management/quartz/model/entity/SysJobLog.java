package com.linck.management.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linck.management.quartz.model.enums.JobResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * SysJobLog对象
 *
 * @author linck
 * @date 2021-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysJobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * job的Id
     */
    private Long jobId;

    /**
     * 描述
     */
    private String msg;

    /**
     * 结果 1-成功 0-失败
     */
    private JobResultEnum result;

    /**
     * 运行花费时间
     */
    private Integer spendTime;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

}
