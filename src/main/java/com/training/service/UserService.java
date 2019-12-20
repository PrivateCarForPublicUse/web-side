package com.training.service;

import com.training.domain.User;
import com.training.response.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    ResponseResult getUsers();
    ResponseResult getUserById(Long id);
    ResponseResult save(User user);
    ResponseResult update(User user);
    ResponseResult delete(Long id);
    ResponseResult getUserByPhone(String s);

    ResponseResult getUserByAccount(Long id);
}
