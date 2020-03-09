package com.training.controller;


import com.training.domain.Master;
import com.training.domain.User;
import com.training.model.ChgPwdModel;
import com.training.response.ResponseResult;
import com.training.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value="/user",tags="用户信息接口")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;


    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="对象不存在"),
    })

    @ApiOperation("获取用户列表")
    @GetMapping("/")
    public ResponseResult users(){
        return userService.getUsers();
    }



    @ApiOperation("通过id获取用户信息")
    @ApiImplicitParam(value = "用户id",paramType = "path")
    @GetMapping("/{id}")
    public ResponseResult getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @ApiOperation("添加用户")
    @ApiImplicitParam(value = "用户对象",paramType = "query")
    @PostMapping("/user")
    public ResponseResult add(@RequestBody User user){
        return userService.save(user);
    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(value = "用户对象",paramType = "query")
    @PutMapping("/user")
    public ResponseResult update(@RequestBody User user){
        return userService.update(user);
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(value = "用户id",paramType = "path")
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id){
        return userService.delete(id);
    }

    @ApiOperation("根据审核状态返回用户,审核状态：-1审核不通过,0未审核,1审核通过")
    @ApiImplicitParam(value="审核状态",name="status")
    @GetMapping("/status")
    public ResponseResult getByStatus(@RequestParam int status){return userService.getUsersByCheckStatus(status);}

    @ApiOperation("修改传到后台的用户的所有数据")
    @PutMapping("/fd/update")
    public ResponseResult updateFDUser(@RequestBody User user){return userService.updateFDUser(user);}

    @ApiOperation("用户修改密码")
    @PostMapping("/change-password")
    public ResponseResult changePassword(HttpServletRequest request,@RequestBody ChgPwdModel chgPwdModel){
        User user=(User)request.getSession().getAttribute("user");
        if(user == null)
            return new ResponseResult("用户未登录");
        return userService.changePassword(user,chgPwdModel);
    }

}
