package ${package.Controller};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
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

import java.util.List;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
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
    @Autowired
    private ${table.serviceName} ${serviceInstanceName};

    /**
    * 查询${table.comment!}列表
    */
    @GetMapping("page")
    public Result<ListWithPage<${entity}>> list(QueryCondition<${entity}> condition) {
        QueryWrapper<${entity}> queryWrapper = condition.dealQueryCondition(${entity}.class);
        Page<${entity}> page = ${serviceInstanceName}.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    /**
    * 新增${table.comment!}
    */
    @PostMapping
    public Result<${entity}> add(@RequestBody SysRole sysRole) {
        if (sysRole.getValue() == null) {
            return Result.failed("内容不能为空");
        }
        return Result.success(${serviceInstanceName}.insert(sysRole));
    }

    /**
    * 修改${table.comment!}
    */
    @PutMapping
    public Result<String> update(@RequestBody ${entity} sysRole) {
        ${serviceInstanceName}.updateById(sysRole);
        return Result.success("");
    }

    /**
    * 删除${table.comment!}
    */
    @DeleteMapping
    public Result<Integer> remove(@RequestBody @Validated IdsDto ids) {
        return Result.success(${serviceInstanceName}.remove(ids));
    }
}
</#if>
