package com.linck.management.quartz.model.dto;

import com.linck.management.common.model.dto.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author linck
 **/

@Data
@EqualsAndHashCode(callSuper = false)
public class SysJobLogDto extends PageDto {

    /**
     * 主键id
     */
    private Long id;

    /**
     * job的Id
     */
    private Long jobId;

    /**
     * 描述
     */
    private String msg;

    /**
     * 结果 1-成功 0-失败
     */
    private Integer result;
}
