package com.linck.management.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDTO;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.quartz.model.dto.SysJobDTO;
import com.linck.management.quartz.model.entity.SysJob;
import com.linck.management.quartz.service.SysJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-29 21:35
 **/
@Slf4j
@Api(tags = "Job管理")
@RestController
@RequestMapping("sys/job")
public class SysJobController {

    @Autowired
    private SysJobService sysJobService;

    @ApiOperation("job列表")
    @PostMapping("list")
    public Result<ListWithPage<SysJob>> list(@RequestBody SysJobDTO sysJobDTO) {
        List<SysJob> list = sysJobService.list(sysJobDTO);
        PageInfo<SysJob> pageInfo = new PageInfo<>(list);
        ListWithPage<SysJob> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

    @ApiOperation("新增job")
    @PostMapping("add")
    @PreAuthorize("hasAuthority('job:add')")
    public Result add(@RequestBody SysJob sysJob) {
        sysJob.setCreateTime(new Date());
        sysJob.setUpdateTime(new Date());
        sysJobService.save(sysJob);
        return Result.success(sysJob.getId());
    }

    @ApiOperation("更新job")
    @PostMapping("update")
    @PreAuthorize("hasAuthority('job:update')")
    public Result update(@RequestBody SysJob sysJob) {
        sysJob.setUpdateTime(new Date());
        sysJobService.updateById(sysJob);
        return Result.success("");
    }

    @ApiOperation("删除job")
    @PostMapping("remove")
    @PreAuthorize("hasAuthority('job:remove')")
    public Result remove(@RequestBody IdDTO idDTO) {
        sysJobService.removeById(idDTO.getId());
        return Result.success("");
    }

    @ApiOperation("启动Job")
    @PostMapping("start")
    public Result start(@RequestBody IdDTO idDTO) {
        sysJobService.start(idDTO.getId());
        return Result.success("");
    }

    @ApiOperation("停止Job")
    @PostMapping("stop")
    public Result stop(@RequestBody IdDTO idDTO) {
        sysJobService.stop(idDTO.getId());
        return Result.success("");
    }

}
