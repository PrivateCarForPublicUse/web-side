package com.training.controller;

import com.training.domain.Reimburse;
import com.training.service.ReimburseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * by Huang
 * 报销增删改查
 */
@Api(value="/reimburse",tags="报销接口")
@RequestMapping("/reimburse")
@RestController
public class ReimburseController {
    @Autowired
    ReimburseService reimburseService;

    @ApiOperation("获取报销列表")
    @GetMapping("/")
    public List<Reimburse> reimburses(){
        return reimburseService.getReimburses();
    }



    @ApiOperation("通过id获取报销信息")
    @ApiImplicitParam(value = "报销id",paramType = "path")
    @GetMapping("/{id}")
    public Reimburse getCarById(@PathVariable("id") Long id){
        return reimburseService.getReimburseById(id);
    }

    @ApiOperation("更新某条报销状态")
    @ApiImplicitParam(value = "报销对象",paramType = "query")
    @PutMapping("/reimburse")
    public Reimburse update(Reimburse reimburse){
        return reimburseService.save(reimburse);
    }

}
