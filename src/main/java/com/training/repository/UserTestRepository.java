package com.training.repository;

import com.training.domain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestRepository extends JpaRepository<UserTest,Long> {
}
