package com.training.repository;

import com.training.domain.Account;
import com.training.domain.User;
import com.training.domain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
//    @Query(value="select * from user_test where id = :id",nativeQuery = true)
//    List<UserTest> findById(@Param("id")Integer id);
    @Query(value="select * from user where phone_number = :s",nativeQuery = true)
    User findByPhoneNumber(@Param("s")String s);
    @Query(value="select * from user where account_id = :id",nativeQuery = true)
    User findByAccount(@Param("id")Long id);
    @Query(value="select * from user where user_name = :user_name",nativeQuery = true)
    User getUserByUserName(@Param("user_name")String userName);
    //根据用户审核状态查询
    List<User> getUsersByCheckStatus(int status);
    //根据id获取
    User getUserById(Long id);

    List<User> getUsersByCheckStatusAndCompanyId(int status,Long companyId);

    List<User> getUserByCompanyId(Long companyId);
}
