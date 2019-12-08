package com.training.controller;

import com.training.domain.Car;
import com.training.response.ResponseResult;
import com.training.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * by Huang
 * 车辆增删改查
 */
@Api(value="/car",tags="车辆信息接口")
@RequestMapping("/car")
@RestController
public class CarController {
    @Autowired
    CarService carService;

    @ApiOperation("获取车辆列表")
    @GetMapping("/")
    public ResponseResult cars(){
        return carService.getCars();
    }

    @ApiOperation("查看没有被删除的所有车")
    @ApiImplicitParam(name="isDeleted",value = "是否已删除")
    @PostMapping("/isDeleted/{isDeleted}")
    public ResponseResult findByIsDeleted(@PathVariable("isDeleted") int isDeleted){
        return carService.findByIsDeleted(isDeleted);
    }

    @ApiOperation("通过id获取车辆信息")
    @ApiImplicitParam(name="id",value = "车辆id")
    @GetMapping("/id/{id}")
    public ResponseResult getCarById(@PathVariable("id") Long id){
        return carService.getCarById(id);
    }

    @ApiOperation("添加车辆")
    @ApiImplicitParam(name="car",value = "车辆对象")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Car car){
        return carService.save(car);
    }

    @ApiOperation("更新车辆")
    @ApiImplicitParam(name="car",value = "车辆对象")
    @PutMapping("/update")
    public ResponseResult update(@RequestBody Car car){
        return carService.save(car);
    }

    @ApiOperation("删除车辆")
    @ApiImplicitParam(name="id",value = "车辆id")
    @DeleteMapping("/delete/{id}")
    public ResponseResult delete(@PathVariable("id") Long id){
        return carService.delete(id);
    }

    @ApiOperation("根据公私状态1公车，0私车获取对应的车辆列表")
    @ApiImplicitParam(name="isPublic",value = "是否为私车")
    @PostMapping("/isPublic/{isPublic}")
    public ResponseResult findByIsPublic(@PathVariable("isPublic") int isPublic){
        return carService.findByIsPublic(isPublic);
    }

    @ApiOperation("根据使用状态0空闲,1审核中,2使用中获取对应的车辆列表")
    @ApiImplicitParam(name="isUse",value = "使用状态")
    @PostMapping("/isUse/{isUse}")
    public ResponseResult findByIsUse(@PathVariable("isUse") int isUse){
        return carService.findByIsUse(isUse);
    }


}
