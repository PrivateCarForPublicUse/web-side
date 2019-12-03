package com.training.web;

import com.training.model.UserTestModel;
import com.training.response.ResponseResult;
import com.training.service.UserTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value="/userTest",tags="用于测试的用户相关接口")
@Controller
@RequestMapping("/userTest")
public class UserTestController {
    @Autowired
    UserTestService userTestService;

    @ApiOperation("插入、查询的接口")
    @GetMapping("/")
    @ResponseBody
    public ResponseResult getAll(){
        return userTestService.getUsers();
    }

    @ApiOperation("按名字查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", defaultValue = "李四")
    })
    @GetMapping("/name")
    @ResponseBody
    public ResponseResult getAllUsersByName(@RequestParam("name") String name){
        return userTestService.getUsersByName(name);
    }
    @ApiOperation("post请求根据用户名查询用户")
    @PostMapping("/user")
    @ResponseBody
    public ResponseResult getUsers2(@RequestBody UserTestModel userTestModel){
        return userTestService.getUsersByName(userTestModel.getName());
    }
}
