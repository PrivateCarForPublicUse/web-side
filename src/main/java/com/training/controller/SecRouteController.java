package com.training.controller;

import com.training.domain.SecRoute;
import com.training.response.ResponseResult;
import com.training.service.SecRouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//涉及到参数传递的目前用不了，得等前端页面做出来
@Api(value="/SecRoute",tags="用于测试段路程表相关接口")
@RequestMapping("/SecRoute")
@RestController
public class SecRouteController {
    @Autowired
    SecRouteService secRouteService;

    @ApiOperation("查询段路程表所有信息")
    @GetMapping("/")
    public ResponseResult findAllSecRoute() {
        return secRouteService.findAllSecRoute();
    }

    @ApiOperation("根据行程id获取所有段行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "行程id")
    })
    @GetMapping("/id")
    public ResponseResult findSecRouteById(@RequestParam("id")Long id) {
        return secRouteService.findSecRouteById(id);
    }

    @ApiOperation("根据行程id查找段行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeid", value = "行程id")
    })
    @GetMapping("/routeid")
    public ResponseResult findSecRouteByRouteId(@RequestParam("routeid")Long routeid) {
        return secRouteService.findSecRouteByRouteId(routeid);
    }

    @ApiOperation("保存段行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "secroute", value = "新增的段行程")
    })
    @PostMapping("/save")
    public ResponseResult saveSecRoute(@RequestBody SecRoute secroute) {
        return secRouteService.saveSecRoute(secroute);
    }

    @ApiOperation("更新段行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "secroute", value = "更新的段行程")
    })
    @PostMapping("/update")
    public ResponseResult updateSecRoute(@RequestBody SecRoute secroute) {
        return secRouteService.updateSecRoute(secroute);
    }

    /*
    public void deleteSecRoute(SecRoute secroute) {
        secRouteService.deleteSecRoute(secroute);
    }

    public void deleteSecRouteById(@RequestParam("id")Long id) {
        secRouteService.deleteSecRouteById(id);
    }

    public void deleteSecRouteByRouteId(@RequestParam("routeid")Long routeid) {
        secRouteService.deleteSecRouteByRouteId(routeid);
    }

 */
}
