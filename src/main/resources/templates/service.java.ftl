package ${package.Service};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;

/**
* ${table.comment!}Service
* @author ${author}
* @create ${date}
*/
@Service
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>  {

    }
</#if>
