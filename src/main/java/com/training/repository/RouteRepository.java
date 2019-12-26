package com.training.repository;

import com.training.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RouteRepository extends JpaRepository<Route,Long>{

    // 根据carid查询，使用sql语句
    @Query(value="select * from route where car_id = :carid",nativeQuery = true)
    List<Route> findRouteByCarId(@Param("carid") Long carid);

    // 根据userId查询，使用sql语句
    @Query(value="select * from route where user_id = :userId",nativeQuery = true)
    List<Route> findRouteByUserId(@Param("userId") Long userId);

    // 根据userId和status查询，使用sql语句
    @Query(value="select * from route where user_id = :userId and status = 3",nativeQuery = true)
    List<Route> findRouteByUserIdAndStatus(@Param("userId") Long userId);

    // 根据审核状态和公司Id返回行程，使用sql语句
    @Query(value="select * from route where user_id in (select id from user where company_id = :companyId) and status = :status",nativeQuery = true)
    List<Route> findRoutesByStatus(int status,Long companyId);

    //根据id返回数据
    Route findRouteById(Long id);

    //根据报销状态返回行程
    List<Route> findRoutesByIsReimburse(int is);
}
