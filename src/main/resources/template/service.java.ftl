package ${package.Service};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;

/**
* ${table.comment!} Service
* @author ${author}
* @date ${date}
*/
@Slf4j
@Service
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}>  {

    /**
    * 分页查询
    */
    public List<${entity}> selectByPage(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        return baseMapper.selectList(null);
    }

    /**
    * 新增
    */
    public SysRole insert(${entity} ${entity[0..0]?lower_case}${entity[1..]}) {
        baseMapper.insert(sysRole);
        return sysRole;
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
    * 删除
    */
    public int remove(IdsDto ids) {
        if (ids.getIds().isEmpty()) {
            return 0;
        }
        return baseMapper.deleteBatchIds(ids.getIds());
    }

    /**
    * 修改
    */
    public int update(${entity} ${entity[0..0]?lower_case}${entity[1..]}) {
        return baseMapper.updateById(sysRole);
    }
}
</#if>
