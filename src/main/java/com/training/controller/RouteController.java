package com.training.controller;

import com.training.domain.Route;
import com.training.response.ResponseResult;
import com.training.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//涉及到参数传递的目前用不了，得等前端页面做出来
@Api(value="/Route",tags="用于测试路程表相关接口")
@Controller
@RequestMapping("/Route")
@RestController
public class RouteController {
    @Autowired
    RouteService routeService;

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

    @ApiOperation("按employeeId查询行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "员工id")
    })
    @GetMapping("/employeeId")
    public ResponseResult findRouteByEmployeeId(@RequestParam("employeeId")Long employeeId) {
        return routeService.findRouteByEmployeeId(employeeId);
    }

    @ApiOperation("保存行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "route", value = "新增的行程")
    })
    @GetMapping("/save")
    public ResponseResult saveRoute(Route route) {
        return routeService.saveRoute(route);
    }

    @ApiOperation("更新行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "route", value = "更新的路程")
    })
    @GetMapping("/update")
    public ResponseResult updateRoute(Route route) {
        return routeService.updateRoute(route);
    }

    @ApiOperation("更新行程状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "行程表id"),
            @ApiImplicitParam(name = "st", value = "行程状态改变值")
    })
    @GetMapping("/update_status")
    public ResponseResult updateStatusOfRouteById(@RequestParam("id")Long id, @RequestParam("st")int st) {
        return routeService.updateStatusOfRouteById(id, st);
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
