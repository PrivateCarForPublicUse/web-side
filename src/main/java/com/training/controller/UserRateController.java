package com.training.controller;

import com.training.domain.UserRate;
import com.training.response.ResponseResult;
import com.training.service.UserRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * by Huang
 * 对借车人的评价增删改查
 */
@Api(value="/userRate",tags="对借车人评价接口")
@RequestMapping("/userRate")
@RestController
public class UserRateController {
    @Autowired
    UserRateService userRateService;

    @ApiResponses({
            @ApiResponse(code=500,message="不存在任何评价")
    })
    @ApiOperation("获取所有评价列表")
    @GetMapping("/")
    public ResponseResult userRates(){
        return userRateService.getUserRates();
    }

    @ApiResponses({
            @ApiResponse(code=502,message="该用户没有发表过任何评价")
    })
    @ApiOperation("根据评论人id获取其发表的所有对借车人的评论")
    @ApiImplicitParam(name="userId",value = "评论人id")
    @GetMapping("/userId/{userId}")
    public ResponseResult findByUserId(@PathVariable("userId") Long userId){
        return userRateService.findByUserId(userId);
    }

    @ApiResponses({
            @ApiResponse(code=503,message="该用户没有受到过任何评价")
    })
    @ApiOperation("根据被评论人id获取其受到的所有评论")
    @ApiImplicitParam(name="evaluateeId",value = "被评论人id")
    @GetMapping("/evaluateeId/{evaluateeId}")
    public ResponseResult findByEvaluateeId(@PathVariable("evaluateeId") Long evaluateeId){
        return userRateService.findByEvaluateeId(evaluateeId);
    }

    @ApiResponses({
            @ApiResponse(code=501,message="新建失败")
    })
    @ApiOperation("新增评价")
    @ApiImplicitParam(name="userRate",value = "评价内容")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserRate userRate){
        return userRateService.save(userRate);
    }

}
