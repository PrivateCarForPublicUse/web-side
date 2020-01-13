package com.training.service;

import com.training.domain.Message;
import com.training.response.ResponseResult;

import java.util.List;

public interface MessageService {
    ResponseResult save(Message message);
    ResponseResult findAllByTableNameAndIdInTable(String tableName, Long idInTable);
}
