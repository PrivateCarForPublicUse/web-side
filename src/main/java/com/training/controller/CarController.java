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
}
