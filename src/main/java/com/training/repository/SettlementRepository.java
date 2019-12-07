package com.training.repository;

import com.training.domain.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SettlementRepository extends JpaRepository<Settlement,Long> {
    // 根据secRouteId查询，使用sql语句
    @Query(value="select * from settlement where secRouteId = :secrouteid",nativeQuery = true)
    public List<Settlement> findSettlementBySecRouteId(@Param("secrouteid") Long secrouteid);

    // 根据RouteId查询段行程，使用sql语句
    @Query(value="select * from settlement where routeId = :routeid",nativeQuery = true)
    public List<Settlement> findSettlementByRouteId(@Param("routeid") Long routeid);

    // 根据employeeId查询，使用sql语句
    @Query(value="select * from settlement where employeeId = :employeeId",nativeQuery = true)
    public List<Settlement> findSettlementByEmployeeId(@Param("employeeId") Long employeeId);
}
