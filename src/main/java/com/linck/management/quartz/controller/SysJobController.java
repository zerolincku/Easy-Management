package com.linck.management.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.quartz.model.dto.SysJobDto;
import com.linck.management.quartz.model.entity.SysJob;
import com.linck.management.quartz.service.SysJobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Job管理
 *
 * @author linck
 **/
@Slf4j
@RestController
@RequestMapping("sys/job")
public class SysJobController {

    @Autowired
    private SysJobService sysJobService;

    /**
     * job列表
     */
    @PostMapping("list")
    public Result<ListWithPage<SysJob>> list(@RequestBody SysJobDto sysJobDto) {
        List<SysJob> list = sysJobService.list(sysJobDto);
        PageInfo<SysJob> pageInfo = new PageInfo<>(list);
        ListWithPage<SysJob> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

    /**
     * 新增job
     */
    @PostMapping("add")
    @PreAuthorize("hasAuthority('job:add')")
    public Result<Long> add(@RequestBody SysJob sysJob) {
        sysJob.setCreateTime(LocalDateTime.now());
        sysJob.setUpdateTime(LocalDateTime.now());
        sysJobService.save(sysJob);
        return Result.success(sysJob.getId());
    }

    /**
     * 更新job
     */
    @PostMapping("update")
    @PreAuthorize("hasAuthority('job:update')")
    public Result<String> update(@RequestBody SysJob sysJob) {
        sysJob.setUpdateTime(LocalDateTime.now());
        sysJobService.updateById(sysJob);
        return Result.success("");
    }

    /**
     * 删除job
     */
    @PostMapping("remove")
    @PreAuthorize("hasAuthority('job:remove')")
    public Result<String> remove(@RequestBody IdDto idDto) {
        sysJobService.removeById(idDto.getId());
        return Result.success("");
    }

    /**
     * 启动Job
     */
    @PostMapping("start")
    public Result<String> start(@RequestBody IdDto idDto) {
        sysJobService.start(idDto.getId());
        return Result.success("");
    }

    /**
     * 停止Job
     */
    @PostMapping("stop")
    public Result<String> stop(@RequestBody IdDto idDto) {
        sysJobService.stop(idDto.getId());
        return Result.success("");
    }

}
