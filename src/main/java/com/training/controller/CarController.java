package com.training.controller;

import com.training.domain.Car;
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
    public List<Car> cars(){
        return carService.getCars();
    }



    @ApiOperation("通过id获取车辆信息")
    @ApiImplicitParam(value = "车辆id",paramType = "path")
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable("id") Long id){
        return carService.getCarById(id);
    }

    @ApiOperation("添加车辆")
    @ApiImplicitParam(value = "车辆对象",paramType = "query")
    @PostMapping("/car")
    public Car add(@RequestBody Car car){
        return carService.save(car);
    }

    @ApiOperation("更新车辆")
    @ApiImplicitParam(value = "车辆对象",paramType = "query")
    @PutMapping("/car")
    public Car update(@RequestBody Car car){
        return carService.save(car);
    }

    @ApiOperation("删除车辆")
    @ApiImplicitParam(value = "车辆id",paramType = "path")
    @DeleteMapping("/{id}")
    public Car delete(@PathVariable("id") Long id){
        return carService.delete(id);
    }
}
