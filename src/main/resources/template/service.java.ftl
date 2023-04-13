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

    /**
    * 新增
    */
    public ${entity} insert(${entity} ${entityInstanceName}) {
        baseMapper.insert(${entityInstanceName});
        return ${entityInstanceName};
    }

    /**
    * 批量新增
    */
    public int batchInsert(List<${entity}> list) {
        if (list.isEmpty()) {
            return 0;
        }
        return baseMapper.batchInsert(list);
    }

    /**
    * 修改
    */
    public int update(${entity} ${entityInstanceName}) {
        return baseMapper.updateById(${entityInstanceName});
    }

    /**
    * 删除
    */
    public int remove(IdsDto ids) {
        if (ids.getIds().isEmpty()) {
            return 0;
        }
        return baseMapper.deleteBatchIds(ids.getIds());
    }

}
</#if>
