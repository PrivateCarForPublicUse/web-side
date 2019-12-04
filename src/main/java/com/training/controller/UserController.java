package com.training.controller;


import com.training.domain.User;
import com.training.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("获取用户列表")
    @GetMapping("/")
    public List<User> users(){
        return userService.getUsers();
    }
    @ApiOperation("通过id获取用户信息")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }
}
