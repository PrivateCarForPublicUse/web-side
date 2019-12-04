package com.training.service.Impl;


import com.training.domain.User;
import com.training.repository.UserRepository;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }
}
