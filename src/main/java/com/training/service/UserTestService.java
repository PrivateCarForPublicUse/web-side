package com.training.service;

import com.training.domain.UserTest;
import com.training.response.ResponseResult;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserTestService {
    ResponseResult getUsers();
    ResponseResult getUsersByName(String name);
}
