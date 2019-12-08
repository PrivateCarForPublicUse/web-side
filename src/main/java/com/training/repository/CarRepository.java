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
    // 根据isPublic查询
    @Query(value="select * from car where is_public = :is_public",nativeQuery = true)
    public List<Car> findByIsPublic(@Param("is_public") int isPublic);
    // 根据isUse查询
    @Query(value="select * from car where is_use = :is_use",nativeQuery = true)
    public List<Car> findByIsUse(@Param("is_use") int isUse);
    // 根据isDeleted查询
    @Query(value="select * from car where is_deleted = :is_deleted",nativeQuery = true)
    public List<Car> findByIsDeleted(@Param("is_deleted") int isDeleted);
}
