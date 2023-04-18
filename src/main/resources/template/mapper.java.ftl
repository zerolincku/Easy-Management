package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};

import java.util.List;

/**
 * ${table.comment!} Mapper
 *
 * @author ${author}
 * @date ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
}
</#if>
