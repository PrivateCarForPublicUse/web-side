package com.training.repository;

import com.training.domain.User;
import com.training.domain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
//    @Query(value="select * from user_test where id = :id",nativeQuery = true)
//    List<UserTest> findById(@Param("id")Integer id);
}
