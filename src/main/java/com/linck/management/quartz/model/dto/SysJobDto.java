package com.linck.management.quartz.model.dto;

import com.linck.management.common.model.dto.PageDto;
import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author linck
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class SysJobDto extends PageDto {

    /**
     * 主键id
     */
    private Long id;

    /**
     * job类
     */
    private String jobClass;

    /**
     * job名称
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
     * 触发器name)
     */
    private String triggerName;

    /**
     * 组
     */
    private String group;

}
