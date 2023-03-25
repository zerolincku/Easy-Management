package com.linck.management.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.quartz.model.dto.SysJobLogDto;
import com.linck.management.quartz.model.entity.SysJobLog;
import com.linck.management.quartz.service.SysJobLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Job日志管理
 *
 * @author linck
 **/
@Slf4j
@RestController
@RequestMapping("sys/job/log")
public class SysJobLogController {

    @Autowired
    private SysJobLogService sysJobLogService;

    /**
     * job日志列表
     */
    @PreAuthorize("hasAuthority('role:view')")
    @PostMapping("list")
    public Result<ListWithPage<SysJobLog>> list(@RequestBody SysJobLogDto sysJobLogDto) {
        List<SysJobLog> list = sysJobLogService.list(sysJobLogDto);
        PageInfo<SysJobLog> pageInfo = new PageInfo<>(list);
        ListWithPage<SysJobLog> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

}
