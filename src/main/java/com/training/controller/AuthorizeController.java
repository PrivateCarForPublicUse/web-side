package com.training.controller;


import com.training.domain.Account;
import com.training.domain.User;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="/authorize",tags="验证接口")
@RequestMapping("/authorize")
@RestController
public class AuthorizeController {

    @Autowired AccountService accountService;

    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="密码错误"),
    })

    @PostMapping("/login")
    public ResponseResult login(@RequestBody Account account){
        ResponseResult account2 = accountService.getAccountById(account.getId());
        Account account1 = (Account)account2.getData();
        if(account2!=null&&account1.getPassword().equals(account.getPassword())){
            return account2;
        }
        else return new ResponseResult(500,"账号密码错误",null);
    }

    @PostMapping("/login/token")
    public ResponseResult loginByToken(@RequestBody Account account){
        ResponseResult account2 = accountService.getAccountByToken(account.getToken());
        if(account2.getData()!=null){
            return account2;
        }
        else return new ResponseResult(500,"令牌无效",null);
    }

}
