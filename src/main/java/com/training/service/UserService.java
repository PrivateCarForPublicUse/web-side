package com.training.service;

import com.training.domain.User;
import com.training.response.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();
    User getUserById(Long id);
}
