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
public class QrtzSimpleTriggers extends Model<QrtzSimpleTriggers> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SCHED_NAME", type = IdType.ASSIGN_ID)
    private String schedName;

    @TableField("TRIGGER_NAME")
    private String triggerName;

    @TableField("TRIGGER_GROUP")
    private String triggerGroup;

    @TableField("REPEAT_COUNT")
    private Long repeatCount;

    @TableField("REPEAT_INTERVAL")
    private Long repeatInterval;

    @TableField("TIMES_TRIGGERED")
    private Long timesTriggered;

    public String getSchedName() {
        return schedName;
    }

    public void setSchedName(String schedName) {
        this.schedName = schedName;
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
    public Long getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Long repeatCount) {
        this.repeatCount = repeatCount;
    }
    public Long getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Long repeatInterval) {
        this.repeatInterval = repeatInterval;
    }
    public Long getTimesTriggered() {
        return timesTriggered;
    }

    public void setTimesTriggered(Long timesTriggered) {
        this.timesTriggered = timesTriggered;
    }

    @Override
    protected Serializable pkVal() {
        return this.schedName;
    }

    @Override
    public String toString() {
        return "QrtzSimpleTriggers{" +
            "schedName=" + schedName +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", repeatCount=" + repeatCount +
            ", repeatInterval=" + repeatInterval +
            ", timesTriggered=" + timesTriggered +
        "}";
    }
}
