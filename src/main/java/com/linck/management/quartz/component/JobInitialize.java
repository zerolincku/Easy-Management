package com.linck.management.quartz.component;

import cn.hutool.core.util.ClassUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linck.management.quartz.job.AbstractJob;
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

import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 扫描指定包下的所有 Job, 如果 Job 类，没有注册到 SysJob 表的话，会自动注册到该张表
 * @author lck
 */
@Slf4j
@Component
@Order(1)
public class JobInitialize implements ApplicationRunner {
    @Autowired
    private SysJobMapper sysJobMapper;

    private static final Map<Class<? extends AbstractJob>, Long> jobIdMap = new HashMap<>();

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
                    sysJob.setCron("* */1 * * * ?");
                    sysJob.setGroupName("default");
                    sysJob.setCreateAt(LocalDateTime.now());
                    sysJob.setUpdateAt(LocalDateTime.now());
                    sysJobMapper.insert(sysJob);
                }
                jobIdMap.put((Class<? extends AbstractJob>) clazz, sysJob.getId());
            }
        }
    }

    public static Long getJobId(Class<? extends AbstractJob> clazz) {
        return jobIdMap.get(clazz);
    }
}
