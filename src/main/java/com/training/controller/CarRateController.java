package com.training.controller;

import com.training.domain.CarRate;
import com.training.response.ResponseResult;
import com.training.service.CarRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * by Huang
 * 对车的评价增删改查
 */
@Api(value="/carRate",tags="对车的评价接口")
@RequestMapping("/carRate")
@RestController
public class CarRateController {
    @Autowired
    CarRateService carRateService;

    @ApiResponses({
            @ApiResponse(code=500,message="不存在任何评价")
    })
    @ApiOperation("获取所有评价列表")
    @GetMapping("/")
    public ResponseResult carRates(){
        return carRateService.getCarRates();
    }

    @ApiResponses({
            @ApiResponse(code=501,message="新建失败")
    })
    @ApiOperation("新增评价")
    @ApiImplicitParam(name="carRate",value = "评价内容")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody CarRate carRate){
        return carRateService.save(carRate);
    }

    @ApiResponses({
            @ApiResponse(code=502,message="该用户没有发表过任何评价")
    })
    @ApiOperation("根据评论人id获取其发表的所有对车的评论")
    @ApiImplicitParam(name="userId",value = "评论人id")
    @GetMapping("/userId/{userId}")
    public ResponseResult findByUserId(@PathVariable("userId") Long userId){
        return carRateService.findByUserId(userId);
    }

    @ApiResponses({
            @ApiResponse(code=503,message="该车未曾收到过评价")
    })
    @ApiOperation("根据车的id获取该车的所有评价")
    @ApiImplicitParam(name="carId",value = "车的id")
    @GetMapping("/carId/{carId}")
    public ResponseResult findByEvaluateeId(@PathVariable("carId") Long carId){
        return carRateService.findByCarId(carId);
    }
}
