package com.training.repository;

import com.training.domain.Master;
import com.training.domain.Route;
import com.training.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Transactional
public interface MasterRepository extends JpaRepository<Master,Long>{
    // 根据name查询，使用sql语句
    @Query(value="select * from master where name = :name",nativeQuery = true)
    List<Master> findAllByName(@Param("name") String name);

    //根据id返回数据
    Master findMasterByMasterName(String mastername);


    @Query(value="select * from master where account_id = :accountId",nativeQuery = true)
    Master findMasterByACountId(@Param("accountId")Long id);

    @Query(value="select * from master where master_name = :masterName",nativeQuery = true)
    Master getMasterByMasterName(@Param("masterName")String masterName);

    List<Master> findMasterByCompanyId(Long id);
}
