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
        return new ResponseResult(carRepository.findAll());
    }

    @Override
    public ResponseResult getCarById(Long id) {
        return new ResponseResult(carRepository.findById(id).get());
    }

    @Override
    public ResponseResult save(Car car){
        return new ResponseResult(carRepository.save(car));
    }

    @Override
    public ResponseResult update(Car car) {
        return new ResponseResult(carRepository.save(car));
    }

    @Override
    public ResponseResult delete(Long id){
        Car car = carRepository.findById(id).get();
        carRepository.deleteById(id);
        return new ResponseResult(car);
    }

    @Override
    public ResponseResult findByIsPublic(int isPublic) {
        return new ResponseResult(carRepository.findByIsPublic(isPublic));
    }

    @Override
    public ResponseResult findByIsUse(int isUse) {
        return new ResponseResult(carRepository.findByIsUse(isUse));
    }

    @Override
    public ResponseResult findByIsDeleted(int isDeleted) {
        return new ResponseResult(carRepository.findByIsDeleted(isDeleted));
    }
}
