package com.linck.management.quartz.model.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-10-29 21:40
 **/
public class SysJobDTO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("job类")
    private String jobClass;

    @ApiModelProperty("job名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("cron")
    private String cron;

    @ApiModelProperty("启用状态(1-启用 0-禁用)")
    private Integer state;

    @ApiModelProperty("触发器name)")
    private String triggerName;

    @ApiModelProperty("触发器组")
    private String triggerGroup;

    private Integer pageNum;

    private Integer pageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getTriggerName() {
        return triggerName;
    }

    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    public String getTriggerGroup() {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup) {
        this.triggerGroup = triggerGroup;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
