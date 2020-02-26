package com.training.repository;

import com.training.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query(value="select city from company group by city",nativeQuery = true)
    List<String> findCities();
    @Query(value="select * from company where city = :city",nativeQuery = true)
    List<Company> findCompaniesByCity(@Param("city") String city);
    List<Company> findAll();
}
