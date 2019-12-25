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
    @Query(value="select * from route where car_id = :carid",nativeQuery = true)
    public List<Route> findRouteByCarId(@Param("carid") Long carid);

    // 根据userId查询，使用sql语句
    @Query(value="select * from route where user_id = :userId",nativeQuery = true)
    public List<Route> findRouteByUserId(@Param("userId") Long userId);

    // 根据审核状态返回行程
    List<Route> findRoutesByStatus(int status);
}
