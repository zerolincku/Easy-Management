package com.linck.management.quartz.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linck.management.common.util.ClassUtils;
import com.linck.management.quartz.entity.SysJob;
import com.linck.management.quartz.mapper.SysJobMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

/**
 * @author lck
 * @date 2020/10/13 18:23
 * @description
 */
@Component
@Order(1)
public class JobInitialize implements ApplicationRunner {

	private static final Logger log = LoggerFactory.getLogger(JobInitialize.class);

	@Autowired
	private SysJobMapper sysJobMapper;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.debug("开始初始化job包下的class");
		Set<Class<?>> classSet = ClassUtils.getClasses("com.linck.management.quartz.job");
		classSet.forEach( clazz -> {
			log.debug("加载类:{} {}",clazz.getName(),clazz.getSimpleName());
			Integer count = sysJobMapper.selectCount(new QueryWrapper<SysJob>().eq("job_class", clazz.getName()));
			if ( count == 0){
				SysJob sysJob = new SysJob();
				sysJob.setJobClass(clazz.getName());
				sysJob.setName(clazz.getSimpleName());
				sysJob.setCreateTime(new Date());
				sysJob.setUpdateTime(new Date());
				sysJobMapper.insert(sysJob);
			}
		});
	}
}
