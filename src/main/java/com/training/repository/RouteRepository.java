package com.training.repository;

import com.training.domain.Route;
import com.training.domain.Settlement;
import com.training.dto.UserIdAndSumPrice;
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

    // 根据行程状态和公司Id返回行程，使用sql语句
    @Query(value="select * from route where user_id in (select id from user where company_id = :companyId) and status = :status",nativeQuery = true)
    List<Route> findRoutesByStatusAndCompanyId(int status,Long companyId);

    //根据报销状态和公司Id返回行程
    @Query(value="select * from route where user_id in (select id from user where company_id = :companyId) and is_reimburse=:isReimburse",nativeQuery = true)
    List<Route> findRoutesByIsReimburseAndCompanyId(int isReimburse,Long companyId);

    @Query(value = "SELECT user_id ,sum(price) as sum FROM route where  user_id in (select id from user where company_id = :companyId) and is_imburse=1 group by user_id ORDER BY sum DESC",nativeQuery = true)
    List<Object[]> findUserIdAndSumPrice(Long companyId);
    //根据审核状态返回行程
    List<Route> findRoutesByStatus(int status);

    //根据id返回数据
    Route findRouteById(Long id);

    //根据报销状态返回行程
    List<Route> findRoutesByIsReimburse(int is);

    //根据报销状态、用户id、行程状态返回路程
    List<Route> findRoutesByUserIdAndStatusAndIsReimburse(Long userId, int status, int isReimburse);

    //根据报销状态、行程状态返回路程
    List<Route> findRoutesByStatusAndIsReimburse(int status,int isReimburse);

    //根据id返回Route
    List<Route> findRoutesByIdIn(List<Long>ids);

    //根据行程状态、报销状态、公司Id返回行程
    @Query(value="select * from route where status=:status && is_reimburse=:isReimburse && user_id in (select id from user where company_id=:companyId)",nativeQuery = true)
    List<Route> findRoutesByStatusAndIsReimburseAndCompanyId(@Param("status")int status,@Param("isReimburse")int isReimburse,@Param("companyId")Long companyId);


    @Query(value = "SELECT user_id ,count(price) as count FROM route where  user_id in (select id from user where company_id = :companyId) and is_imburse=1 group by user_id ORDER BY count DESC",nativeQuery = true)
    List<Object[]> findUserIdAndSumTimes(Long companyId);

//    @Query(value="select * from settlement WHERE sec_route_id in (SELECT id FROM sec_route WHERE route_id=:routeId)",nativeQuery = true)
//    List<Settlement> findSettlementById(@Param("routeId")Long routeId);
}
