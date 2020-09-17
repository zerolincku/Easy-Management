package com.linck.management.quartz.entity;

import java.math.BigDecimal;
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
public class QrtzSimpropTriggers extends Model<QrtzSimpropTriggers> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SCHED_NAME", type = IdType.ASSIGN_ID)
    private String schedName;

    @TableField("TRIGGER_NAME")
    private String triggerName;

    @TableField("TRIGGER_GROUP")
    private String triggerGroup;

    @TableField("STR_PROP_1")
    private String strProp1;

    @TableField("STR_PROP_2")
    private String strProp2;

    @TableField("STR_PROP_3")
    private String strProp3;

    @TableField("INT_PROP_1")
    private Integer intProp1;

    @TableField("INT_PROP_2")
    private Integer intProp2;

    @TableField("LONG_PROP_1")
    private Long longProp1;

    @TableField("LONG_PROP_2")
    private Long longProp2;

    @TableField("DEC_PROP_1")
    private BigDecimal decProp1;

    @TableField("DEC_PROP_2")
    private BigDecimal decProp2;

    @TableField("BOOL_PROP_1")
    private String boolProp1;

    @TableField("BOOL_PROP_2")
    private String boolProp2;

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
    public String getStrProp1() {
        return strProp1;
    }

    public void setStrProp1(String strProp1) {
        this.strProp1 = strProp1;
    }
    public String getStrProp2() {
        return strProp2;
    }

    public void setStrProp2(String strProp2) {
        this.strProp2 = strProp2;
    }
    public String getStrProp3() {
        return strProp3;
    }

    public void setStrProp3(String strProp3) {
        this.strProp3 = strProp3;
    }
    public Integer getIntProp1() {
        return intProp1;
    }

    public void setIntProp1(Integer intProp1) {
        this.intProp1 = intProp1;
    }
    public Integer getIntProp2() {
        return intProp2;
    }

    public void setIntProp2(Integer intProp2) {
        this.intProp2 = intProp2;
    }
    public Long getLongProp1() {
        return longProp1;
    }

    public void setLongProp1(Long longProp1) {
        this.longProp1 = longProp1;
    }
    public Long getLongProp2() {
        return longProp2;
    }

    public void setLongProp2(Long longProp2) {
        this.longProp2 = longProp2;
    }
    public BigDecimal getDecProp1() {
        return decProp1;
    }

    public void setDecProp1(BigDecimal decProp1) {
        this.decProp1 = decProp1;
    }
    public BigDecimal getDecProp2() {
        return decProp2;
    }

    public void setDecProp2(BigDecimal decProp2) {
        this.decProp2 = decProp2;
    }
    public String getBoolProp1() {
        return boolProp1;
    }

    public void setBoolProp1(String boolProp1) {
        this.boolProp1 = boolProp1;
    }
    public String getBoolProp2() {
        return boolProp2;
    }

    public void setBoolProp2(String boolProp2) {
        this.boolProp2 = boolProp2;
    }

    @Override
    protected Serializable pkVal() {
        return this.schedName;
    }

    @Override
    public String toString() {
        return "QrtzSimpropTriggers{" +
            "schedName=" + schedName +
            ", triggerName=" + triggerName +
            ", triggerGroup=" + triggerGroup +
            ", strProp1=" + strProp1 +
            ", strProp2=" + strProp2 +
            ", strProp3=" + strProp3 +
            ", intProp1=" + intProp1 +
            ", intProp2=" + intProp2 +
            ", longProp1=" + longProp1 +
            ", longProp2=" + longProp2 +
            ", decProp1=" + decProp1 +
            ", decProp2=" + decProp2 +
            ", boolProp1=" + boolProp1 +
            ", boolProp2=" + boolProp2 +
        "}";
    }
}
