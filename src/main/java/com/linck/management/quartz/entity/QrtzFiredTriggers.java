package com.linck.management.quartz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author linck
 * @since 2020-09-17
 */
public class QrtzFiredTriggers extends Model<QrtzFiredTriggers> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SCHED_NAME", type = IdType.ASSIGN_ID)
    private String schedName;

    @TableField("ENTRY_ID")
    private String entryId;

    @TableField("TRIGGER_NAME")
    private String triggerName;

    @TableField("TRIGGER_GROUP")
    private String triggerGroup;

    @TableField("INSTANCE_NAME")
    private String instanceName;

    @TableField("FIRED_TIME")
    private Long firedTime;

    @TableField("SCHED_TIME")
    private Long schedTime;

    @TableField("PRIORITY")
    private Integer priority;

    @TableField("STATE")
    private String state;

    @TableField("JOB_NAME")
    private String jobName;

    @TableField("JOB_GROUP")
    private String jobGroup;

    @TableField("IS_NONCONCURRENT")
    private String isNonconcurrent;

    @TableField("REQUESTS_RECOVERY")
    private String requestsRecovery;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
    }
    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
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
    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }
    public Long getFiredTime() {
        return firedTime;
    }

    public void setFiredTime(Long firedTime) {
        this.firedTime = firedTime;
    }
    public Long getSchedTime() {
        return schedTime;
    }

    public void setSchedTime(Long schedTime) {
        this.schedTime = schedTime;
    }
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }
    public String getIsNonconcurrent() {
        return isNonconcurrent;
    }

    public void setIsNonconcurrent(String isNonconcurrent) {
        this.isNonconcurrent = isNonconcurrent;
    }
    public String getRequestsRecovery() {
        return requestsRecovery;
    }

    public void setRequestsRecovery(String requestsRecovery) {
        this.requestsRecovery = requestsRecovery;
    }

    @Override
    protected Serializable pkVal() {
        return this.schedName;
    }

    @Override
    public String toString() {
        return "QrtzFiredTriggers{" +
            "schedName=" + schedName +
            ", entryId=" + entryId +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", instanceName=" + instanceName +
            ", firedTime=" + firedTime +
            ", schedTime=" + schedTime +
            ", priority=" + priority +
            ", state=" + state +
            ", jobName=" + jobName +
            ", jobGroup=" + jobGroup +
            ", isNonconcurrent=" + isNonconcurrent +
            ", requestsRecovery=" + requestsRecovery +
        "}";
    }
}
