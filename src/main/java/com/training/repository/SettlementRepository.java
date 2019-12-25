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
    @Query(value="select * from settlement where sec_route_id = :secrouteid",nativeQuery = true)
    public List<Settlement> findSettlementBySecRouteId(@Param("secrouteid") Long secrouteid);

    // 根据RouteId查询段行程，使用sql语句
    @Query(value="select * from settlement where route_id = :routeid",nativeQuery = true)
    public List<Settlement> findSettlementByRouteId(@Param("routeid") Long routeid);

    // 根据userId查询，使用sql语句
    @Query(value="select * from settlement where user_id = :userId",nativeQuery = true)
    public List<Settlement> findSettlementByUserId(@Param("userId") Long userId);

    // 返回对应的路程的报销状态来返回结算表信息
}
