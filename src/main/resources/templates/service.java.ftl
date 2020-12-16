package ${package.Service};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
* ${table.comment!}Service
* @author ${author}
* @create ${date}
*/
@Slf4j
@Service
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
    public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>  {

    }
</#if>
