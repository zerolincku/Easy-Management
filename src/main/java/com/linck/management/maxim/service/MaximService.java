package com.linck.management.maxim.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.maxim.entity.Maxim;
import com.linck.management.maxim.mapper.MaximMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service
 *
 * @author linck
 * @create 2020-12-16
 */
@Slf4j
@Service
@DS("secondary")
public class MaximService extends ServiceImpl<MaximMapper, Maxim> {

}
