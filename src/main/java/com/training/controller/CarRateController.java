package com.training.controller;

import com.training.domain.CarRate;
import com.training.service.CarRateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("获取评价列表")
    @GetMapping("/")
    public List<CarRate> carRates(){
        return carRateService.getCarRates();
    }

    @ApiOperation("新增评价")
    @ApiImplicitParam(value = "评价内容",paramType = "query")
    @PostMapping("/carRate")
    public CarRate add(CarRate carRate){
        return carRateService.save(carRate);
    }

}
