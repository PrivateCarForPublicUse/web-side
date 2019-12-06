package com.training.service;

import com.training.domain.Car;

import java.util.List;

/**
 * by Huang
 */
public interface CarService {
    List<Car> getCars();
    Car getCarById(Long id);
    Car save(Car car);
    Car update(Car car);
    Car delete(Long id);
}
