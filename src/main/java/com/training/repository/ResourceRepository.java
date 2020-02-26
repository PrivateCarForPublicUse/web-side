package com.training.repository;

import com.training.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ResourceRepository extends JpaRepository<Resource,Long> {
    List<Resource> findAllByNum(String num);
}
