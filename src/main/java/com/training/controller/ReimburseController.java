package com.training.controller;

import com.training.domain.Reimburse;
import com.training.response.ResponseResult;
import com.training.service.ReimburseService;
import io.swagger.annotations.*;
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

    @ApiResponses({
            @ApiResponse(code=500,message="不存在任何记录")
    })
    @ApiOperation("获取所有报销列表")
    @GetMapping("/")
    public ResponseResult reimburses(){
        return reimburseService.getReimburses();
    }

    @ApiResponses({
            @ApiResponse(code=501,message="没有未报销的行程"),
            @ApiResponse(code=502,message="没有已报销的行程"),
            @ApiResponse(code=503,message="没有报销失败的行程"),
            @ApiResponse(code=509,message="没有待审核的行程"),
            @ApiResponse(code=504,message="无效的识别码")
    })
    @ApiOperation("根据-1报销失败，0未报销，1已报销，2审核中查看其中一种状态的报销列表")
    @ApiImplicitParam(name = "isReimburse",value = "报销状态")
    @GetMapping("/isReimburse/{isReimburse}")
    public ResponseResult getReimbursesByStatus(@PathVariable("isReimburse") int isReimburse){
        return reimburseService.getReimbursesByStatus(isReimburse);
    }

    @ApiResponses({
            @ApiResponse(code=505,message="id不存在")
    })
    @ApiOperation("通过id获取报销信息")
    @ApiImplicitParam(name = "id", value = "主键id")
    @GetMapping("/id/{id}")
    public ResponseResult getReimburseById(@PathVariable("id") Long id){
        return reimburseService.getReimburseById(id);
    }

    @ApiResponses({
            @ApiResponse(code=506,message="routeId不存在")
    })
    @ApiOperation("通过RouteId获取报销信息")
    @ApiImplicitParam(name = "routeId",value = "行程id")
    @GetMapping("/routeId/{routeId}")
    public ResponseResult getReimburseByRouteId(@PathVariable("routeId") Long routeId){
        return reimburseService.getReimburseByRouteId(routeId);
    }

    @ApiResponses({
            @ApiResponse(code=507,message="修改失败")
    })
    @ApiOperation("发出报销申请")
    @ApiImplicitParam(name = "routeIds",value = "行程id的列表")
    @PutMapping("/apply")
    public ResponseResult update(@RequestParam("routeIds")List<Long> routeIds){
        return reimburseService.applyReimburse(routeIds);
    }

}
