package com.training.service.Impl;

import com.training.domain.Message;
import com.training.repository.MessageRepository;
import com.training.response.ResponseResult;
import com.training.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public ResponseResult save(Message message){
        try {
            Message m = messageRepository.save(message);
            return new ResponseResult(message);
        }
        catch (Exception e){
            return new ResponseResult(501,"保存失败!");
        }
    }

    @Override
    public ResponseResult findAllByTableNameAndIdInTable(String tableName, Long idInTable) {
        List<Message>messages=messageRepository.findAllByTableNameAndIdInTable(tableName,idInTable);
        if(messages==null||messages.size()==0)return new ResponseResult(500,"不存在反馈");
        return new ResponseResult(messages.get(0));
    }
}
