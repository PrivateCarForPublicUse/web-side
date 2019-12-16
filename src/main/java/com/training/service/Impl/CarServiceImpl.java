package com.training.service.Impl;

import com.training.domain.Car;
import com.training.repository.CarRepository;
import com.training.response.ResponseResult;
import com.training.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by Huang
 */
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepository carRepository;

    @Override
    public ResponseResult getCars(){
        List<Car> cars = carRepository.findAll();
        if (cars.size() == 0)
            return new ResponseResult(500,"不存在任何车辆!");
        return new ResponseResult(cars);
    }

    @Override
    public ResponseResult getCarById(Long id) {
        try {
            Car car = carRepository.findById(id).get();
            return new ResponseResult(car);
        }
        catch (Exception e) {
            return new ResponseResult(501, "id不存在!");
        }
    }

    @Override
    public ResponseResult save(Car car){
        try {
            Car c = carRepository.save(car);
            return new ResponseResult(c);
        }
        catch (Exception e){
            return new ResponseResult(502,"新建失败!");
        }
    }

    @Override
    public ResponseResult update(Car car) {
        try {
            Car c = carRepository.save(car);
            return new ResponseResult(c);
        }
        catch (Exception e){
            return new ResponseResult(503,"更新失败!");
        }
    }

    @Override
    public ResponseResult delete(Long id){
        try {
            carRepository.deleteById(id);
            return new ResponseResult("删除成功!");
        }
        catch (Exception e){
            return new ResponseResult(504,"删除失败!");
        }
    }

    @Override
    public ResponseResult findByIsPublic(int isPublic) {
        List<Car> cars = carRepository.findByIsPublic(isPublic);
        if (cars.size() == 0){
            switch (isPublic){
                case 0:return new ResponseResult(505,"没有私车!");
                case 1:return new ResponseResult(506,"没有公车!");
                default:return new ResponseResult(507,"无效的识别码!");
            }
        }
        return new ResponseResult(cars);
    }

    @Override
    public ResponseResult findByIsUse(int isUse) {
        List<Car> cars = carRepository.findByIsUse(isUse);
        if (cars.size() == 0){
            switch (isUse){
                case 0:return new ResponseResult(508,"没有空闲的车!");
                case 1:return new ResponseResult(509,"没有审核中的车!");
                case 2:return new ResponseResult(510,"没有使用中的车!");
                default:return new ResponseResult(507,"无效的识别码!");
            }
        }
        return new ResponseResult(cars);
    }

    @Override
    public ResponseResult findByIsDeleted(int isDeleted) {
        List<Car> cars = carRepository.findByIsDeleted(isDeleted);
        if (cars.size() == 0){
            switch (isDeleted){
                case 0:return new ResponseResult(511,"不存在有效的车辆!");
                case -1:return new ResponseResult(512,"没有已删除的车!");
                default:return new ResponseResult(507,"无效的识别码!");
            }
        }
        return new ResponseResult(cars);
    }
}
