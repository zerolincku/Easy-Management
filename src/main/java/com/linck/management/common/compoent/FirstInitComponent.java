package com.linck.management.common.compoent;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author lck
 * @date 2020/10/13 18:23
 * @description
 */
@Component
@Order(1)
public class FirstInitComponent implements ApplicationRunner {
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Spring初始化后第一个执行的方法");
	}
}
