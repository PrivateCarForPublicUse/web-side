package com.training.service.Impl;

import com.training.domain.CarRate;
import com.training.repository.CarRateRepository;
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
    public List<CarRate> getCarRates(){
        return carRateRepository.findAll();
    }

    @Override
    public CarRate save(CarRate carRate){
        return carRateRepository.save(carRate);
    }

    @Override
    public CarRate update(CarRate carRate) {
        return carRateRepository.save(carRate);
    }

}
