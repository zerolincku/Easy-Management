package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.common.validate.Insert;
import com.linck.management.common.validate.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Api(tags = "${table.comment!}")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    <#assign serviceInstanceName="${table.serviceName[0..0]?lower_case}${table.serviceName[1..]}"/>
    <#assign entityInstanceName="${entity[0..0]?lower_case}${entity[1..]}"/>
    @Autowired
    private ${table.serviceName} ${serviceInstanceName};

    @ApiOperation("查询${table.comment!}列表")
    @GetMapping("page")
    public Result<ListWithPage<${entity}>> page(QueryCondition<${entity}> condition) {
        QueryWrapper<${entity}> queryWrapper = condition.dealQueryCondition(${entity}.class);
        Page<${entity}> page = ${serviceInstanceName}.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    @ApiOperation("新增${table.comment!}")
    @PostMapping
    public Result<String> add(@RequestBody @Validated(Insert.class) ${entity} ${entityInstanceName}) {
        ${serviceInstanceName}.save(${entityInstanceName});
        return Result.success();
    }

    @ApiOperation("修改${table.comment!}")
    @PutMapping
    public Result<String> update(@RequestBody @Validated(Update.class) ${entity} ${entityInstanceName}) {
        ${serviceInstanceName}.updateById(${entityInstanceName});
        return Result.success();
    }

    @ApiOperation("删除${table.comment!}")
    @DeleteMapping
    public Result<String> remove(@RequestBody @Validated IdsDto idsDto) {
        ${serviceInstanceName}.removeBatchByIds(idsDto.getIds());
        return Result.success();
    }

    @ApiOperation("查询${table.comment!}")
    @GetMapping
    public Result<${entity}> get(@RequestBody @Validated IdDto idDto) {
        return Result.success(${serviceInstanceName}.getById(idDto.getId()));
    }
}
</#if>
