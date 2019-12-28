package com.training.controller;

import com.training.domain.Account;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/account",tags="账号接口")
@RequestMapping("/account")
@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="对象不存在"),
    })

    @ApiOperation("获取账号列表")
    @GetMapping("/")
    public ResponseResult users(){
        return accountService.getAccounts();
    }



    @ApiOperation("通过id获取账号信息")
    @ApiImplicitParam(value = "账号id",paramType = "path")
    @GetMapping("/{id}")
    public ResponseResult getAccountById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }

    @ApiOperation("添加账号")
    @ApiImplicitParam(value = "账号对象",paramType = "query")
    @PostMapping("/user")
    public ResponseResult add(@RequestBody Account user){
        return accountService.save(user);
    }

    @ApiOperation("更新账号")
    @ApiImplicitParam(value = "账号对象",paramType = "query")
    @PutMapping("/user")
    public ResponseResult update(@RequestBody Account user){
        return accountService.update(user);
    }

    @ApiOperation("删除账号")
    @ApiImplicitParam(value = "账号id",paramType = "path")
    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable("id") Long id){
        return accountService.delete(id);
    }
}
