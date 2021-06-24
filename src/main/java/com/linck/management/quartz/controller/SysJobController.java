package com.linck.management.quartz.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.IdModel;
import com.linck.management.common.model.ListWithPage;
import com.linck.management.quartz.entity.SysJob;
import com.linck.management.quartz.model.dto.SysJobDTO;
import com.linck.management.quartz.service.SysJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        // 如果没有分页参数，初始化参数
        sysJobDTO.ifNotPageSetDefault();
        List<SysJob> list = sysJobService.list(sysJobDTO);
        PageInfo<SysJob> pageInfo = new PageInfo<>(list);
        ListWithPage<SysJob> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

    @ApiOperation("新增job")
    @PostMapping("add")
    public Result add(@RequestBody SysJob sysJob) {
        sysJob.setCreateTime(new Date());
        sysJob.setUpdateTime(new Date());
        sysJobService.save(sysJob);
        return Result.success(sysJob.getId());
    }

    @ApiOperation("更新job")
    @PostMapping("update")
    public Result update(@RequestBody SysJob sysJob) {
        sysJob.setUpdateTime(new Date());
        sysJobService.updateById(sysJob);
        return Result.success("");
    }

    @ApiOperation("删除job")
    @PostMapping("remove")
    public Result remove(@RequestBody IdModel idModel) {
        sysJobService.removeById(idModel.getId());
        return Result.success("");
    }

}
