package com.training.controller;


import com.training.domain.User;
import com.training.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/user",tags="用户信息接口")
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
    @ApiImplicitParam(value = "用户id",paramType = "path")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @ApiOperation("添加用户")
    @ApiImplicitParam(value = "用户对象",paramType = "query")
    @PostMapping("/user")
    public User add(User user){
        return userService.save(user);
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(value = "用户对象",paramType = "query")
    @PutMapping("/user")
    public User update(User user){
        return userService.save(user);
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(value = "用户id",paramType = "path")
    @DeleteMapping("/{id}")
    public User delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }
}
