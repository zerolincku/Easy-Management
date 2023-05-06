package com.linck.management.common.aop;

import com.linck.management.common.model.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 拦截 update 方法，置空 createAt 和 createBy
 *
 * @author linck
 * @since 2023-05-05
 */
@Slf4j
@Aspect
@Component
public class UpdateAspect {
    @Before("execution(* com.linck.management..*.*Controller.update(com.linck.management.common.model.BaseEntity+)) && args(entity)")
    public void beforeUpdate(JoinPoint joinPoint, BaseEntity entity) {
        if (log.isDebugEnabled()) {
            log.debug("UpdateAspect 前置处理，置空 createAt 和 createBy");
        }
        entity.setCreateAt(null);
        entity.setCreateBy(null);
    }
}
