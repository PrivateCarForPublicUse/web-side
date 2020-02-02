package com.training.repository;

import com.training.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MessageRepository extends JpaRepository<Message,Long> {
    //根据tableName、IdInTable查询Message
    List<Message> findAllByTableNameAndIdInTable(String tableName,Long idInTable);
    Message findByTableNameAndIdInTable(String tableName,Long idInTable);
}
