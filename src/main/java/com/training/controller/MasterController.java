package com.training.controller;

import com.training.domain.Master;
import com.training.response.ResponseResult;
import com.training.service.MasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//涉及到参数传递的目前用不了，得等前端页面做出来
@Api(value="/Master",tags="用于测试管理员相关接口")
@RequestMapping("/Master")
@Controller
public class MasterController {
    @Autowired
    MasterService masterService;

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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "master", value = "新增的管理员")
    })
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

    @ApiOperation("根据id查询并更新管理员名字")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id"),
            @ApiImplicitParam(name = "name", value = "修改后的管理员名字")
    })
    @PostMapping("/update")
    public ResponseResult updateUser(@RequestParam("id")Long id, @RequestParam("name")String name) {
        return masterService.updateNameOfMastersById(id, name);
    }
}
