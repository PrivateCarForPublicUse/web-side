package com.training.controller;

import com.training.domain.UserRate;
import com.training.response.ResponseResult;
import com.training.service.UserRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("获取所有评价列表")
    @GetMapping("/")
    public ResponseResult userRates(){
        return userRateService.getUserRates();
    }

    @ApiOperation("根据评论人id获取其发表的所有对借车人的评论")
    @ApiImplicitParam(name="userId",value = "评论人id")
    @PostMapping("/userId/{userId}")
    public ResponseResult findByUserId(@PathVariable("userId") Long userId){
        return userRateService.findByUserId(userId);
    }

    @ApiOperation("根据被评论人id获取其受到的所有评论")
    @ApiImplicitParam(name="evaluateeId",value = "被评论人id")
    @PostMapping("/evaluateeId/{evaluateeId}")
    public ResponseResult findByEvaluateeId(@PathVariable("evaluateeId") Long evaluateeId){
        return userRateService.findByEvaluateeId(evaluateeId);
    }

    @ApiOperation("新增评价")
    @ApiImplicitParam(name="userRate",value = "评价内容")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserRate userRate){
        return userRateService.save(userRate);
    }

}
