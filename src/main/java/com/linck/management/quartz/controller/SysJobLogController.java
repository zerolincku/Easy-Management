package com.linck.management.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.quartz.model.dto.SysJobLogDTO;
import com.linck.management.quartz.model.entity.SysJobLog;
import com.linck.management.quartz.service.SysJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-22 20:34
 **/
@Slf4j
@Api(tags = "Job日志管理")
@RestController
@RequestMapping("sys/job/log")
public class SysJobLogController {

    @Autowired
    private SysJobLogService sysJobLogService;

    @ApiOperation("job日志列表")
    @PostMapping("list")
    public Result<ListWithPage<SysJobLog>> list(@RequestBody SysJobLogDTO sysJobLogDTO) {
        List<SysJobLog> list = sysJobLogService.list(sysJobLogDTO);
        PageInfo<SysJobLog> pageInfo = new PageInfo<>(list);
        ListWithPage<SysJobLog> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

}
