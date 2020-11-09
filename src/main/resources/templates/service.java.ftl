package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;

/**
* <p>
    * ${table.comment!} 服务类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>  {

    }
</#if>