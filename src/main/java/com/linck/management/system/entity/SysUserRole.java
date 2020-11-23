package com.linck.management.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author linck
 * @create 2020-11-04
 */
@ApiModel(value="SysUserRole对象", description="")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long uId;

    @ApiModelProperty(value = "角色id")
    private Long rId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }
    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public SysUserRole() {
    }

    public SysUserRole(Long id, Long uId, Long rId) {
        this.id = id;
        this.uId = uId;
        this.rId = rId;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
            "id=" + id +
            ", uId=" + uId +
            ", rId=" + rId +
        "}";
    }
}
