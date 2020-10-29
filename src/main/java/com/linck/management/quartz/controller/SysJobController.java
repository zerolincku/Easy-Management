package com.linck.management.quartz.controller;

import com.linck.management.common.api.CommonResult;
import com.linck.management.quartz.entity.SysJob;
import com.linck.management.quartz.model.dto.SysJobDTO;
import com.linck.management.quartz.service.SysJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-10-29 21:35
 **/
@Api(tags = "Job管理")
@RestController
@RequestMapping("sysJob")
public class SysJobController {

    private static final Logger log = LoggerFactory.getLogger(SysJobController.class);

    @Autowired
    private SysJobService sysJobService;

    @ApiOperation("job列表")
    @PostMapping("list")
    public CommonResult<List<SysJob>> list(@RequestBody(required = false) SysJobDTO sysJobDTO) {
        if (sysJobDTO == null) {
            sysJobDTO = new SysJobDTO();
        }
        if (sysJobDTO.getPageNum() == null || sysJobDTO.getPageSize() == null) {
            sysJobDTO.setPageNum(1);
            sysJobDTO.setPageSize(10);
        }
        List<SysJob> list = sysJobService.list(sysJobDTO);
        return CommonResult.success(list);
    }

}
