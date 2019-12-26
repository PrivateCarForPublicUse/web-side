package com.training.repository;

import com.training.domain.CarRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * by Huang
 */
public interface CarRateRepository extends JpaRepository<CarRate,Long> {
    // 根据userId查询
    @Query(value="select * from car_rate where user_id = :user_id",nativeQuery = true)
    List<CarRate> findByUserId(@Param("user_id") Long userId);
    // 根据carId查询
    @Query(value="select * from car_rate where car_id = :car_id",nativeQuery = true)
    List<CarRate> findByCarId(@Param("car_id") Long carId);
}
