package ${package.Entity};

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if swagger2??>


</#if>
<#if entityLombokModel>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.common.model.BaseEntity;
import com.linck.management.common.validate.Insert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Null;

/**
 * @author ${author}
 * @date ${date}
 */
<#if entityLombokModel>
@Data
    <#if superEntityClass??>
@EqualsAndHashCode(callSuper = true)
    <#else>
@EqualsAndHashCode(callSuper = false)
    </#if>
@Accessors(chain = true)
</#if>
<#if table.convert>
@TableName("${table.name}")
</#if>
@ApiModel(value="${entity}对象", description="${table.comment!}")
<#if superEntityClass??>
public class ${entity} extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity} extends Model<${entity}> {
<#else>
public class ${entity} extends BaseEntity implements Serializable {
</#if>
<#if entitySerialVersionUID>
    private static final long serialVersionUID = 1L;
</#if>
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    <#if field.keyFlag>
        <#assign keyPropertyName="${field.propertyName}"/>
    </#if>
    <#if field.comment!?length gt 0>
    <#if field.propertyName != "createBy" && field.propertyName != "createAt" && field.propertyName != "updateBy" && field.propertyName != "updateAt" && field.propertyName != "delFlag" && field.propertyName != "version">
    @ApiModelProperty(value = "${field.comment}")
    </#if>
    </#if>
    <#if field.keyFlag>
    <#-- 主键 -->
    <#if field.keyIdentityFlag>
    @TableId(value = "${field.name}", type = IdType.AUTO)
    @Null(groups = Insert.class, message = "新增时id需要为空")
        <#elseif idType??>
    @TableId(value = "${field.name}", type = IdType.${idType})
    @Null(groups = Insert.class, message = "新增时id需要为空")
        <#elseif field.convert>
    @TableId("${field.name}")
    @Null(groups = Insert.class, message = "新增时id需要为空")
    </#if>
    <#-- 普通字段 -->
    <#elseif field.fill??>
    <#-- -----   存在字段填充设置   ----->
    <#if field.convert>
    @TableField(value = "${field.name}", fill = FieldFill.${field.fill})
    <#else>
    @TableField(fill = FieldFill.${field.fill})
    </#if>
    <#elseif field.convert>
    @TableField("${field.name}")
    </#if>
    <#-- 乐观锁注解 -->
    <#if (versionFieldName!"") == field.name>
    @Version
    </#if>
    <#-- 逻辑删除注解 -->
    <#if (logicDeleteFieldName!"") == field.name>
    @TableLogic
    </#if>
    <#if field.propertyName == "status">
    private StatusEnum ${field.propertyName};
    <#elseif field.propertyName == "createBy" || field.propertyName == "createAt" || field.propertyName == "updateBy" || field.propertyName == "updateAt" || field.propertyName == "delFlag" || field.propertyName == "version">
    <#else>
    private ${field.propertyType} ${field.propertyName};
    </#if>
</#list>
<#------------  END 字段循环遍历  ---------->
<#if !entityLombokModel>
    <#list table.fields as field>
        <#if field.propertyType == "boolean">
            <#assign getprefix="is"/>
        <#else>
            <#assign getprefix="get"/>
        </#if>
    public ${field.propertyType} ${getprefix}${field.capitalName}() {
        return ${field.propertyName};
    }
        <#if entityBuilderModel>
    public ${entity} set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        <#else>
    public void set${field.capitalName}(${field.propertyType} ${field.propertyName}) {
        </#if>
        this.${field.propertyName} = ${field.propertyName};
        <#if entityBuilderModel>
        return this;
        </#if>
    }
    </#list>
</#if>
<#if entityColumnConstant>
    <#list table.fields as field>
    public static final String ${field.name?upper_case} = "${field.name}";
    </#list>
</#if>
<#if activeRecord>
    @Override
    protected Serializable pkVal() {
    <#if keyPropertyName??>
        return this.${keyPropertyName};
    <#else>
        return null;
    </#if>
    }
</#if>
<#if !entityLombokModel>
    @Override
    public String toString() {
        return "${entity}{" +
    <#list table.fields as field>
        <#if field_index==0>
        "${field.propertyName}=" + ${field.propertyName} +
        <#else>
        ", ${field.propertyName}=" + ${field.propertyName} +
        </#if>
    </#list>
        "}";
    }
</#if>
}
