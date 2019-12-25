package com.training.repository;

import com.training.domain.SecRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SecRouteRepository extends JpaRepository<SecRoute,Long> {

    // 根据RouteId查询段行程，使用sql语句
    @Query(value="select * from sec_route where route_id = :routeid",nativeQuery = true)
    public List<SecRoute> findSecRouteByRouteId(@Param("routeid") Long routeid);

    // 根据RouteId删除段行程，使用sql语句
    @Modifying
    @Query(value="delete from sec_route where route_id = :routeid",nativeQuery = true)
    public void deleteSecRouteByRouteId(@Param("routeid") Long routeid);

}
