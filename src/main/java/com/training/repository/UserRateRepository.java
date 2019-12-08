package com.training.repository;

import com.training.domain.UserRate;
import com.training.domain.UserRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * by Huang
 */
public interface UserRateRepository extends JpaRepository<UserRate,Long> {
    // 根据userId查询
    @Query(value="select * from user_rate where user_id = :user_id",nativeQuery = true)
    public List<UserRate> findByUserId(@Param("user_id") Long userId);
    // 根据evaluateeId查询
    @Query(value="select * from user_rate where evaluatee_id = :evaluatee_id",nativeQuery = true)
    public List<UserRate> findByEvaluateeId(@Param("evaluatee_id") Long evaluateeId);

}
