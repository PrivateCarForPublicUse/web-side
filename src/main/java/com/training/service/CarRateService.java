package com.training.service;

import com.training.domain.CarRate;
import com.training.response.ResponseResult;

import java.util.List;

/**
 * by Huang
 */
public interface CarRateService {
    ResponseResult getCarRates(Long companyId);
    ResponseResult save(CarRate carRate);
    ResponseResult update(CarRate carRate);
    ResponseResult findByUserId(Long userId);
    ResponseResult findByCarId(Long carId);
}
