package com.linck.management.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author linck
 * @since 2020-10-13
 */
public class SysJob extends Model<SysJob> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * job类路径
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
    private Integer state;

    /**
     * 触发器name
     */
    private String triggerName;

    /**
     * 触发器组
     */
    private String triggerGroup;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysJob{" +
                "id=" + id +
                ", jobClass=" + jobClass +
                ", name=" + name +
                ", description=" + description +
                ", cron=" + cron +
                ", state=" + state +
                ", triggerName=" + triggerName +
                ", triggerGroup=" + triggerGroup +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
