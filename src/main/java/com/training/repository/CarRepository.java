package com.training.repository;

import com.training.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * by Huang
 */
public interface CarRepository extends JpaRepository<Car,Long> {

}
