package com.linck.management.quartz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.quartz.model.entity.SysJobLog;
import com.linck.management.quartz.service.SysJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linck
 **/
@Api(tags = "Job日志管理")
@Slf4j
@RestController
@RequestMapping("sys/job/log")
public class SysJobLogController {

    @Autowired
    private SysJobLogService sysJobLogService;

    @ApiOperation("查询任务日志列表")
    @PreAuthorize("hasAuthority('role:view')")
    @GetMapping("page")
    public Result<ListWithPage<SysJobLog>> page(QueryCondition<SysJobLog> condition) {
        QueryWrapper<SysJobLog> queryWrapper = condition.dealQueryCondition(SysJobLog.class);
        Page<SysJobLog> page = sysJobLogService.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

}
