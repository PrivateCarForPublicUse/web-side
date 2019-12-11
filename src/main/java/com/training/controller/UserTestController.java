package com.training.controller;

import com.training.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value="/userTest",tags="测试")
public class UserTestController {
    @GetMapping("/test")
    @ApiOperation("接口的作用")
    @ApiImplicitParams({
            @ApiImplicitParam(value="账号",name="id"),
            @ApiImplicitParam(value="姓名",name="name")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=501,message="账号不存在"),
            @ApiResponse(code=502,message="name不存在")
    })
    public ResponseResult test(Long id,String name){
        if(id==0)return new ResponseResult(501,"账号不存在");
        else if(name.equals(""))return new ResponseResult(502,"name不存在");
        return new ResponseResult();
    }
}
