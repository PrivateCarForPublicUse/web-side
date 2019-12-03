package com.training.service.Impl;

import com.training.domain.UserTest;
import com.training.repository.UserTestRepository;
import com.training.response.ResponseResult;
import com.training.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTestServiceImpl implements UserTestService {
    @Autowired
    UserTestRepository userTestRepository;
    //测试，增加user，并返回所有user
    @Override
    public ResponseResult getUsers() {
        userTestRepository.save(new UserTest("new"));
        return new ResponseResult(userTestRepository.findAll());
    }
    //根据名字查询用户
    @Override
    public ResponseResult getUsersByName(String name) {
        return new ResponseResult(userTestRepository.findAllByName(name));
    }
}
