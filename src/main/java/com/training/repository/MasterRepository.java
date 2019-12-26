package com.training.repository;

import com.training.domain.Master;
import com.training.domain.Route;
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
    public List<Master> findAllByName(@Param("name") String name);

    //根据id返回数据
    Master findMasterByMasterName(String mastername);
}
