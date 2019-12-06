package com.training.service.Impl;

import com.training.domain.Car;
import com.training.repository.CarRepository;
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
    public List<Car> getCars(){
        return carRepository.findAll();
    }

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public Car save(Car car){
        return carRepository.save(car);
    }

    @Override
    public Car update(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car delete(Long id){
        Car car = carRepository.findById(id).get();
        carRepository.deleteById(id);
        return car;
    }
}
