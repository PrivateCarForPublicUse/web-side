package com.training.repository;

import com.training.domain.Account;
import com.training.domain.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AccountRepository extends JpaRepository<Account,Long> {
    @Query(value="select * from account where token = :token",nativeQuery = true)
    Account findByToken(@Param("token")String token);
}
