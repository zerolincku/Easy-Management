package ${package.Service};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.linck.management.common.model.dto.IdsDto;

import java.util.List;

/**
 * ${table.comment!} Service
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Service
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
<#assign serviceInstanceName="${table.serviceName[0..0]?lower_case}${table.serviceName[1..]}"/>
<#assign entityInstanceName="${entity[0..0]?lower_case}${entity[1..]}"/>
public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>  {
}
</#if>
