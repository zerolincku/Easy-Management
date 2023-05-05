package com.linck.management.common.config.mybatis;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.common.util.SpringContextHolder;
import com.linck.management.system.model.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * 通用参数填充实现类
 * <p>
 * 如果没有显式的对通用参数进行赋值，这里会对通用参数进行填充、赋值
 *
 * @author linck
 */
@Slf4j
@Component
public class DefaultDBFieldHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.debug("mybatis plus start insert fill ....");
        SysUser sysUser = SpringContextHolder.getCurrentSysUser();
        LocalDateTime now = LocalDateTime.now();

        fillValIfNullByName("createAt", now, metaObject, true);
        fillValIfNullByName("updateAt", now, metaObject, true);
        fillValIfNullByName("createBy", sysUser.getId(), metaObject, true);
        fillValIfNullByName("updateBy", sysUser.getId(), metaObject, true);
        fillValIfNullByName("status", StatusEnum.ENABLE, metaObject, true);
        fillValIfNullByName("delFlag", false, metaObject, true);
        fillValIfNullByName("version", 1L, metaObject, true);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("mybatis plus start update fill ....");
        LocalDateTime now = LocalDateTime.now();
        SysUser sysUser = SpringContextHolder.getCurrentSysUser();

        fillValIfNullByName("updateAt", now, metaObject, true);
        fillValIfNullByName("updateBy", sysUser.getId(), metaObject, true);
    }

    /**
     * 填充值，先判断是否有手动设置，优先手动设置的值，例如：job必须手动设置
     * @param fieldName 属性名
     * @param fieldVal 属性值
     * @param metaObject MetaObject
     * @param isCover 是否覆盖原有值,避免更新操作手动入参
     */
    private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
        // 1. 没有 set 方法
        if (!metaObject.hasSetter(fieldName)) {
            return;
        }
        // 2. 如果用户有手动设置的值
        Object userSetValue = metaObject.getValue(fieldName);
        String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
        if (StrUtil.isNotBlank(setValueStr) && !isCover) {
            return;
        }
        // 3. field 类型相同时设置
        Class<?> getterType = metaObject.getGetterType(fieldName);
        if (ClassUtils.isAssignableValue(getterType, fieldVal)) {
            metaObject.setValue(fieldName, fieldVal);
        }
    }
}

