package com.linck.management.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * SysJob对象
 * @author linck
 * @create 2020-11-04
 */
@Data
public class SysJob {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 主键id
     */
    private String jobClass;

    /**
     * job名字
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * cron
     */
    private String cron;

    /**
     * 启用状态(1-启用 0-禁用)
     */
    private StatusEnum status;

    /**
     * 触发器name
     */
    private String triggerName;

    /**
     * 组
     */
    private String groupName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
