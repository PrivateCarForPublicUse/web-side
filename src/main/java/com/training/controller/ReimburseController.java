package com.training.controller;

import com.training.domain.Reimburse;
import com.training.response.ResponseResult;
import com.training.service.ReimburseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * by Huang
 * 报销增删改查
 */
@Api(value="/reimburse",tags="查看行程是否已报销的接口")
@RequestMapping("/reimburse")
@RestController
public class ReimburseController {
    @Autowired
    ReimburseService reimburseService;

    @ApiOperation("获取所有报销列表")
    @GetMapping("/")
    public ResponseResult reimburses(){
        return reimburseService.getReimburses();
    }

    @ApiOperation("根据-1报销失败，0未报销，1已报销，查看其中一种状态的报销列表")
    @ApiImplicitParam(name = "isReimburse",value = "报销状态")
    @GetMapping("/isReimburse/{isReimburse}")
    public ResponseResult getReimbursesByStatus(@PathVariable("isReimburse") int isReimburse){
        return reimburseService.getReimbursesByStatus(isReimburse);
    }

    @ApiOperation("通过id获取报销信息")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/id/{id}")
    public ResponseResult getReimburseById(@PathVariable("id") Long id){
        return reimburseService.getReimburseById(id);
    }

    @ApiOperation("通过RouteId获取报销信息")
    @ApiImplicitParam(name = "routeId",value = "行程id")
    @GetMapping("/routeId/{routeId}")
    public ResponseResult getReimburseByRouteId(@PathVariable("routeId") Long routeId){
        return reimburseService.getReimburseByRouteId(routeId);
    }

    @ApiOperation("更新某个行程的报销状态")
    @ApiImplicitParam(name = "reimburse",value = "报销对象")
    @PutMapping("/update")
    public ResponseResult update(@RequestBody Reimburse reimburse){
        return reimburseService.save(reimburse);
    }

}
