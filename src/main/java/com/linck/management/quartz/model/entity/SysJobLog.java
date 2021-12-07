package com.linck.management.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonValue;
import com.linck.management.common.api.ResultCodeEnum;
import com.linck.management.common.exception.CustomException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Entity
 *
 * @author linck
 * @create 2021-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysJobLog对象", description = "")
public class SysJobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "job的Id")
    private Long jobId;

    @ApiModelProperty(value = "描述")
    private String msg;

    @ApiModelProperty(value = "结果 1-成功 0-失败")
    private ResultEnum result;

    @ApiModelProperty(value = "运行花费时间")
    private Integer spendTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @Slf4j
    @Getter
    @AllArgsConstructor
    public enum ResultEnum {

        SUCCESS(1, "成功"),
        FAILURE(0, "失败");

        @JsonValue
        private Integer value;

        private String name;

        @SneakyThrows
        public static ResultEnum getResultEnum(int value) {
            for (ResultEnum resultEnum : ResultEnum.values()) {
                if (resultEnum.value == value) {
                    return resultEnum;
                }
            }
            log.error("没有对应的 Result，value: {}", value);
            throw new CustomException(ResultCodeEnum.NO_RESULT_VALUE, "ResultEnum.getResultEnum");
        }
    }

}
