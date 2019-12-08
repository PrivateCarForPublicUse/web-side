package com.training.repository;

import com.training.domain.Master;
import com.training.domain.Reimburse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * by Huang
 */
public interface ReimburseRepository extends JpaRepository<Reimburse,Long> {
    // 根据isReimburse查询
    @Query(value="select * from reimburse where is_reimburse = :is_reimburse",nativeQuery = true)
    public List<Reimburse> findByStatus(@Param("is_reimburse") int isReimburse);
    // 根据routeId查询
    @Query(value="select * from reimburse where route_id = :route_id",nativeQuery = true)
    public Reimburse findByRouteId(@Param("route_id") Long routeId);
}
