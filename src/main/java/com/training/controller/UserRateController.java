package com.training.controller;

import com.training.domain.UserRate;
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

    @ApiOperation("获取评价列表")
    @GetMapping("/")
    public List<UserRate> userRates(){
        return userRateService.getUserRates();
    }

    @ApiOperation("新增评价")
    @ApiImplicitParam(value = "评价内容",paramType = "query")
    @PostMapping("/userRate")
    public UserRate add(@RequestBody UserRate userRate){
        return userRateService.save(userRate);
    }

}
