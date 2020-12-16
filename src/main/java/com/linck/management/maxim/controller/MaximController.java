package com.linck.management.maxim.controller;

import com.linck.management.maxim.entity.Maxim;
import com.linck.management.maxim.service.MaximService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: linck
 * @create: 2020-12-16
 */
@Api(tags = "maxim")
@RestController
@RequestMapping(value = "maxim")
public class MaximController {

    @Autowired
    private MaximService maximService;

    @PostMapping("list")
    public List<Maxim> list() {
        return maximService.list();
    }
}
