package com.training.controller;

import com.training.domain.CarRate;
import com.training.response.ResponseResult;
import com.training.service.CarRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("获取所有评价列表")
    @GetMapping("/")
    public ResponseResult carRates(){
        return carRateService.getCarRates();
    }

    @ApiOperation("新增评价")
    @ApiImplicitParam(name="carRate",value = "评价内容")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody CarRate carRate){
        return carRateService.save(carRate);
    }

    @ApiOperation("根据评论人id获取其发表的所有对车的评论")
    @ApiImplicitParam(name="userId",value = "评论人id")
    @PostMapping("/userId/{userId}")
    public ResponseResult findByUserId(@PathVariable("userId") Long userId){
        return carRateService.findByUserId(userId);
    }

    @ApiOperation("根据车的id获取该车的所有评价")
    @ApiImplicitParam(name="carId",value = "车的id")
    @PostMapping("/carId/{carId}")
    public ResponseResult findByEvaluateeId(@PathVariable("carId") Long carId){
        return carRateService.findByCarId(carId);
    }
}
