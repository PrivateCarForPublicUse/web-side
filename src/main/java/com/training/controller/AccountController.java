package com.training.controller;

import com.training.domain.Account;
import com.training.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/account",tags="账号接口")
@RequestMapping("/account")
@RestController
public class AccountController {
    @Autowired
    AccountService accountService;
    @ApiOperation("获取账号列表")
    @GetMapping("/")
    public List<Account> users(){
        return accountService.getAccounts();
    }



    @ApiOperation("通过id获取账号信息")
    @ApiImplicitParam(value = "账号id",paramType = "path")
    @GetMapping("/{id}")
    public Account getUserById(@PathVariable("id") Long id){
        return accountService.getAccountById(id);
    }

    @ApiOperation("添加账号")
    @ApiImplicitParam(value = "账号对象",paramType = "query")
    @PostMapping("/user")
    public Account add(Account user){
        return accountService.save(user);
    }

    @ApiOperation("更新账号")
    @ApiImplicitParam(value = "账号对象",paramType = "query")
    @PutMapping("/user")
    public Account update(Account user){
        return accountService.save(user);
    }

    @ApiOperation("删除账号")
    @ApiImplicitParam(value = "账号id",paramType = "path")
    @DeleteMapping("/{id}")
    public Account delete(@PathVariable("id") Long id){
        return accountService.delete(id);
    }
}
