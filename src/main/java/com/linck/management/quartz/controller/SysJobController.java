package com.linck.management.quartz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.common.validate.Insert;
import com.linck.management.common.validate.Update;
import com.linck.management.quartz.model.entity.SysJob;
import com.linck.management.quartz.service.SysJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author linck
 **/
@Api(tags = "Job管理")
@Slf4j
@RestController
@RequestMapping("sys/job")
public class SysJobController {

    @Autowired
    private SysJobService sysJobService;

    @ApiOperation("查询任务列表")
    @GetMapping("page")
    public Result<ListWithPage<SysJob>> page(QueryCondition<SysJob> condition) {
        QueryWrapper<SysJob> queryWrapper = condition.dealQueryCondition(SysJob.class);
        Page<SysJob> page = sysJobService.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    @ApiOperation("新增任务")
    @PostMapping
    public Result<String> add(@RequestBody @Validated(Insert.class) SysJob sysJob) {
        sysJobService.save(sysJob);
        return Result.success();
    }

    @ApiOperation("修改任务")
    @PutMapping
    public Result<String> update(@RequestBody @Validated(Update.class) SysJob sysJob) {
        sysJobService.updateById(sysJob);
        return Result.success();
    }

    @ApiOperation("删除任务")
    @DeleteMapping
    public Result<String> remove(@RequestBody @Validated IdsDto idsDto) {
        sysJobService.removeBatchByIds(idsDto.getIds());
        return Result.success();
    }

    @ApiOperation("查询任务")
    @GetMapping
    public Result<SysJob> get(@RequestBody @Validated IdDto idDto) {
        return Result.success(sysJobService.getById(idDto.getId()));
    }

    @ApiOperation("启动Job")
    @PostMapping("start")
    public Result<String> start(@RequestBody IdDto idDto) {
        sysJobService.start(idDto.getId());
        return Result.success();
    }

    @ApiOperation("停止Job")
    @PostMapping("stop")
    public Result<String> stop(@RequestBody IdDto idDto) {
        sysJobService.stop(idDto.getId());
        return Result.success();
    }

}
