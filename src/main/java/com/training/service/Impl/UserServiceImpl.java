package com.training.service.Impl;


import com.training.domain.User;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseResult getUsers(){
        return new ResponseResult(userRepository.findAll());
    }

    @Override
    public ResponseResult getUserById(Long id) {
        return new ResponseResult(userRepository.findById(id).get());
    }

    @Override
    public ResponseResult save(User user){
        return new ResponseResult(userRepository.save(user));
    }

    @Override
    public ResponseResult update(User user) {
        if(userRepository.findById(user.getId()).isPresent()==false){
            return new ResponseResult(500,"找不到对象",null);
        }
        return new ResponseResult(userRepository.save(user));
    }

    @Override
    public ResponseResult delete(Long id){
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return new ResponseResult(user);
    }

    @Override
    public ResponseResult getUserByPhone(String s) {
        User user = userRepository.findByPhoneNumber(s);
        return new ResponseResult(user);
    }

    @Override
    public ResponseResult getUserByAccount(Long id) {
        User user = userRepository.findByAccount(id);
        return new ResponseResult(user);
    }
}
