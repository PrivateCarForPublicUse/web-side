package com.training.service.Impl;

import com.training.domain.CarRate;
import com.training.repository.CarRateRepository;
import com.training.response.ResponseResult;
import com.training.service.CarRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by Huang
 */
@Service
public class CarRateServiceImpl implements CarRateService {
    @Autowired
    CarRateRepository carRateRepository;

    @Override
    public ResponseResult getCarRates(){
        return new ResponseResult(carRateRepository.findAll());
    }

    @Override
    public ResponseResult save(CarRate carRate){
        return new ResponseResult(carRateRepository.save(carRate));
    }

    @Override
    public ResponseResult update(CarRate carRate) {
        return new ResponseResult(carRateRepository.save(carRate));
    }

    @Override
    public ResponseResult findByUserId(Long userId) {
        return new ResponseResult(carRateRepository.findByUserId(userId));
    }

    @Override
    public ResponseResult findByCarId(Long carId) {
        return new ResponseResult(carRateRepository.findByCarId(carId));
    }

}
