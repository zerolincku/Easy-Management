package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * SysUser对象
 *
 * @author linck
 * @create 2020-11-04
 */
@Data
public class SysUser {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账号
     */
    @NotBlank(message = "账户不能为空")
    @Length(min = 4, max = 32, message = "账户长度需要4-32个字符")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 32, message = "密码长度需要4-32个字符")
    private String pwd;

    /**
     * 状态 1-启用 0-禁用
     */
    private StatusEnum status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
