package ${package.Controller};

import com.jk.mes.common.model.dto.IdDto;
import com.jk.mes.common.model.dto.IdsDto;
import com.jk.mes.${entity?lower_case}.dto.${entity}Dto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
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
@Api(tags = "${table.comment!}")
public class ${table.controllerName} {
</#if>

    @Autowired
    private ${table.serviceName} ${table.serviceName[0..0]?lower_case}${table.serviceName[1..]};

    @PostMapping("list")
    public List<${entity}> list(@RequestBody ${entity}Dto ${entity[0..0]?lower_case}${entity[1..]}Dto) {
        return ${table.serviceName[0..0]?lower_case}${table.serviceName[1..]}.list(${entity[0..0]?lower_case}${entity[1..]}Dto);
    }

    /**
    * 新增
    */
    @PostMapping("add")
    public Result add(@RequestBody SysRole sysRole) {
        if (sysRole.getValue() == null) {
            return Result.failed("内容不能为空");
        }
        return Result.success(sysRoleService.insert(sysRole));
    }

    /**
    * 修改
    */
    @PreAuthorize("hasAuthority('role:update')")
    @PostMapping("update")
    public Result<String> update(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.success("");
    }

    /**
    * 删除
    */
    @PostMapping("remove")
    public Result<Integer> remove(@RequestBody @Validated IdsDto ids) {
        return Result.success(sysRoleService.remove(ids));
    }

    @PostMapping("disable")
    public void disable(@RequestBody @Validated IdsDto idsDto) {
        ${table.serviceName[0..0]?lower_case}${table.serviceName[1..]}.disable(idsDto.getIds());
    }
}
</#if>
