package com.training.controller;

import com.training.domain.Route;
import com.training.response.ResponseResult;
import com.training.service.RouteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//涉及到参数传递的目前用不了，得等前端页面做出来
@Api(value="/Route",tags="用于测试路程表相关接口")
@RequestMapping("/Route")
@RestController
public class RouteController {
    @Autowired
    RouteService routeService;

    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="行程不存在"),
            @ApiResponse(code=501,message="id不存在"),
            @ApiResponse(code=502,message="carid不存在"),
            @ApiResponse(code=503,message="userId不存在"),
            @ApiResponse(code=504,message="插入失败"),
            @ApiResponse(code=505,message="更新失败")
    })
    @ApiOperation("查询所有行程表的接口")
    @GetMapping("/")
    public ResponseResult findAllRoute() {
        return routeService.findAllRoute();
    }

    @ApiOperation("按id查询行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "行程表id")
    })
    @GetMapping("/id")
    public ResponseResult findRouteById(@RequestParam("id")Long id) {
        return routeService.findRouteById(id);
    }

    @ApiOperation("按carid查询行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carid", value = "车辆id")
    })
    @GetMapping("/carid")
    public ResponseResult findRouteByCarId(@RequestParam("carid")Long carid) {
        return routeService.findRouteByCarId(carid);
    }

    @ApiOperation("按userId查询行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "员工id")
    })
    @GetMapping("/userId")
    public ResponseResult findRouteByUserId(@RequestParam("userId")Long userId) {
        return routeService.findRouteByUserId(userId);
    }

    @ApiOperation("保存行程")
    @PostMapping("/save")
    public ResponseResult saveRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @ApiOperation("更新行程")
    @PostMapping("/update")
    public ResponseResult updateRoute(@RequestBody Route route) {
        return routeService.updateRoute(route);
    }

    @ApiOperation("更新行程状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "行程表id"),
            @ApiImplicitParam(name = "st", value = "行程状态改变值")
    })
    @PostMapping("/update_status")
    public ResponseResult updateStatusOfRouteById(@RequestParam("id")Long id, @RequestParam("st")int st) {
        return routeService.updateStatusOfRouteById(id, st);
    }

    @ApiOperation("根据审核状态返回路程信息，返回包含用户、车辆、段路程")
    @ApiImplicitParam(name="status",value="审核状态（-1 审核不通过；0 未审核；1 审核通过；2 行程中；3 已完成；4 已取消）")
    @GetMapping("/status")
    public ResponseResult findRoutesByStatus(@RequestParam("status")int status){
        return routeService.findRoutesByStatus(status);
    }

    @ApiOperation("获取包含所有信息的所有路程")
    @GetMapping("/fd")
    public ResponseResult findFDRoutes(){
        return routeService.findFDRoutes();
    }

    /*
    public void deleteRoute(Route route) {
        routeService.deleteRoute(route);
    }

    public void deleteRouteById(Long id) {
        routeService.deleteRouteById(id);
    }

    public void deleteRouteByCarId(Long carid) {
        routeService.deleteRouteByCarId(carid);
    }

    public void deleteRouteByEmployeeId(Long employeeId) {
        routeService.deleteRouteByEmployeeId(employeeId);
    }
     */
}
