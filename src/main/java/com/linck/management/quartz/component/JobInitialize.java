package com.linck.management.quartz.component;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linck.management.quartz.mapper.SysJobMapper;
import com.linck.management.quartz.model.entity.SysJob;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Set;

/**
 * @author lck
 * @description 扫描Job
 * @date 2020/10/13 18:23
 */
@Slf4j
@Component
@Order(1)
public class JobInitialize implements ApplicationRunner {
    @Autowired
    private SysJobMapper sysJobMapper;

    @Override
    @SneakyThrows
    public void run(ApplicationArguments args) {
        log.debug("开始初始化job包下的class");
        Set<Class<?>> classSet = ClassUtil.scanPackage("com.linck.management.quartz.job");
        for (Class<?> clazz : classSet) {
            // 抽象类和接口不处理同时必须实现 Job 接口
            if (!Modifier.isAbstract(clazz.getModifiers()) && !clazz.isAnnotation() && Job.class.isAssignableFrom(clazz)) {
                log.debug("加载类:{} {}", clazz.getName(), clazz.getSimpleName());
                SysJob sysJob = sysJobMapper.selectOne(new QueryWrapper<SysJob>().eq("job_class", clazz.getName()));
                if (sysJob == null) {
                    sysJob = new SysJob();
                    sysJob.setJobClass(clazz.getName());
                    sysJob.setName(clazz.getSimpleName());
                    sysJob.setCreateTime(new Date());
                    sysJob.setUpdateTime(new Date());
                    sysJobMapper.insert(sysJob);
                }
                Method setJobIdMethod = clazz.getSuperclass().getDeclaredMethod("setJobId", Long.class);
                setJobIdMethod.invoke(null, sysJob.getId());
            }
        }
    }
}
