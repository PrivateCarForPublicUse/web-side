package com.training.repository;

import com.training.domain.Car;
import com.training.domain.CarRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * by Huang
 */
public interface CarRepository extends JpaRepository<Car,Long> {
    // 根据公司ID查询
    @Query(value="select * from car where user_id in (select id from user where company_id = :companyId)",nativeQuery = true)
    List<Car> findAllByCompany(@Param("companyId")Long companyId);
    // 根据isPublic查询
    @Query(value="select * from car where is_public = :is_public",nativeQuery = true)
    List<Car> findByIsPublic(@Param("is_public") int isPublic);
    // 根据isUse查询
    @Query(value="select * from car where is_use = :is_use",nativeQuery = true)
    List<Car> findByIsUse(@Param("is_use") int isUse);
    // 根据isDeleted查询
    @Query(value="select * from car where is_deleted = :is_deleted",nativeQuery = true)
    List<Car> findByIsDeleted(@Param("is_deleted") int isDeleted);
    // 根据time查询My
    @Query(value="select * from car where ((star_time <= :startTime and end_time >= :endTime) or (is_public = 0)) and user_id = :id",nativeQuery = true)
    List<Car> findByTimeAndMy(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("id") Long id);
    // 根据time查询NotMy
    @Query(value="select * from car where is_public = 1 and star_time <= :startTime and end_time >= :endTime and user_id != :userId" +
            " and user_id in (select id from user where company_id = :companyId)",nativeQuery = true)
    List<Car> findByTimeAndNotMy(@Param("startTime") String startTime,@Param("endTime") String endTime,
                                        @Param("userId") Long userId,@Param("companyId") Long companyId);
    // 根据id获取
    Car getCarById(Long id);
    //根据公司ID查看待审核车辆
    @Query(value="select * from car where is_use = 1 and user_id in (select id from user where company_id = :companyId)",nativeQuery = true)
    List<Car> findCarWaitForCheck(@Param("companyId") Long companyId);
    //根据userId和公私用状态获取
    List<Car> findByIsPublicAndUserId(int isPublic,Long userId);
}
