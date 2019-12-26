package com.training.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.training.domain.Master;
import com.training.response.ResponseResult;
import com.training.service.Impl.MasterServiceImpl;
import com.training.service.MasterService;
import com.training.service.RouteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Api(value="/Master",tags="用于测试管理员相关接口")
@RequestMapping("/Master")
@RestController
public class MasterController {
    @Autowired
    RouteService routeService;
    @Autowired
    MasterService masterService;

    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="管理员不存在"),
            @ApiResponse(code=501,message="id不存在"),
            @ApiResponse(code=502,message="name不存在"),
            @ApiResponse(code=503,message="插入失败"),
            @ApiResponse(code=504,message="删除失败"),
            @ApiResponse(code=505,message="更新失败"),
            @ApiResponse(code=506,message="密码错误")
    })


    @ApiOperation("查询所有管理员的接口")
    @GetMapping("/")
    public ResponseResult findAllMasters() {
        return masterService.findAllMasters();
    }

    @ApiOperation("按名字查询管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "管理员名")
    })
    @GetMapping("/name")
    public ResponseResult findMastersByName(@RequestParam("name") String name) {
        return masterService.findMastersByName(name);
    }

    @ApiOperation("按id查询管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id")
    })
    @GetMapping("/id")
    public ResponseResult findMasterById(@RequestParam("id") Long id) {
        return masterService.findMasterById(id);
    }

    @ApiOperation("增加一个新管理员")
    @PostMapping("/add")
    public ResponseResult saveMaster(@RequestBody Master master) {
        masterService.saveMaster(master);
        return masterService.saveMaster(master);
    }

    @ApiOperation("根据id删除一个管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id")
    })
    @DeleteMapping("/del")
    public ResponseResult deleteMaster(@RequestParam("id")Long id) {
        masterService.deleteMasterById(id);
        return new ResponseResult("SUCCESS");
    }

    @ApiOperation("更新管理员")
    @PostMapping("/update")
    public ResponseResult updateUser(@RequestBody Master master) {
        return masterService.updateMaster(master);
    }

    @ApiOperation("根据id查询并更新管理员名字")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id"),
            @ApiImplicitParam(name = "name", value = "修改后的管理员名字")
    })
    @PostMapping("/update_name")
    public ResponseResult updateUser(@RequestParam("id")Long id, @RequestParam("name")String name) {
        return masterService.updateNameOfMastersById(id, name);
    }

    @ApiOperation("管理员审核申请用车")
    @PostMapping("/reviewUseCar")
    public ResponseResult reviewUseCar(@RequestBody String body){
        JSONObject json = JSON.parseObject(body);
        return routeService.updateStatusOfRouteById(json.getLong("routeId"),json.getInteger("status"));
    }

    @ApiOperation("管理员登录接口")
    @PostMapping("/login")
    public ResponseResult loginMaster(@RequestBody Master master){
        return masterService.loginByMasterName(master.getMasterName(),master.getPassword());
    }

}
