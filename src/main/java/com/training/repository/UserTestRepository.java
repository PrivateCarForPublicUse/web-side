package com.training.repository;

import com.training.domain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserTestRepository extends JpaRepository<UserTest,Long> {
    // 根据name查询，使用sql语句
    @Query(value="select * from user_test where name = :name",nativeQuery = true)
    List<UserTest>findAllByName(@Param("name")String name);
}
