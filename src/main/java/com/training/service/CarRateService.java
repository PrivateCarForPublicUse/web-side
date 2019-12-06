package com.training.service;

import com.training.domain.CarRate;

import java.util.List;

/**
 * by Huang
 */
public interface CarRateService {
    List<CarRate> getCarRates();
    CarRate save(CarRate carRate);
    CarRate update(CarRate carRate);
}
