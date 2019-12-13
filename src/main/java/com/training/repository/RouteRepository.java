package com.training.repository;

import com.training.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RouteRepository extends JpaRepository<Route,Long> {

    // 根据carid查询，使用sql语句
    @Query(value="select * from route where carId = :carid",nativeQuery = true)
    public List<Route> findRouteByCarId(@Param("carid") Long carid);

    // 根据userId查询，使用sql语句
    @Query(value="select * from route where userId = :userId",nativeQuery = true)
    public List<Route> findRouteByUserId(@Param("userId") Long userId);


}
