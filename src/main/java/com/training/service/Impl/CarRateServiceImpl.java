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
        List<CarRate> carRates = carRateRepository.findAll();
        if (carRates.size() == 0)
            return new ResponseResult(500,"不存在任何评价!");
        return new ResponseResult(carRates);
    }

    @Override
    public ResponseResult save(CarRate carRate){
        try {
            CarRate c = carRateRepository.save(carRate);
            return new ResponseResult(c);
        }
        catch (Exception e){
            return new ResponseResult(501,"新建失败!");
        }
    }

    @Override
    public ResponseResult update(CarRate carRate) {
        try {
            CarRate c = carRateRepository.save(carRate);
            return new ResponseResult(c);
        }
        catch (Exception e){
            return new ResponseResult(504,"更新失败!");
        }
    }

    @Override
    public ResponseResult findByUserId(Long userId) {
        List<CarRate> carRates = carRateRepository.findByUserId(userId);
        if (carRates.size() == 0)
            return new ResponseResult(502,"该用户没有发表过任何评价!");
        return new ResponseResult(carRates);
    }

    @Override
    public ResponseResult findByCarId(Long carId) {
        List<CarRate> carRates = carRateRepository.findByCarId(carId);
        if (carRates.size() == 0)
            return new ResponseResult(503,"该车未曾收到过评价!");
        return new ResponseResult(carRateRepository.findByCarId(carId));
    }

}
