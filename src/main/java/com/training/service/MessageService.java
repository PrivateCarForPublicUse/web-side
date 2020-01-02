package com.training.service;

import com.training.domain.Message;
import com.training.response.ResponseResult;

import java.util.List;

public interface MessageService {
    ResponseResult findAllByTableNameAndIdInTable(String tableName, Long idInTable);
}
