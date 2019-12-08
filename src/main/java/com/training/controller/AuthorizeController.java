package com.training.controller;


import com.training.domain.Account;
import com.training.domain.User;
import com.training.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizeController {

    @Autowired AccountService accountService;

    @PostMapping("/login")
    public Account login(@RequestBody Account account){
        Account account2 = accountService.getAccountById(account.getId());
        if(account2!=null&&account2.getPassword().equals(account.getPassword())){
            return account2;
        }
        else return null;
    }

    @PostMapping("/login/token")
    public Account loginByToken(@RequestBody Account account){
        Account account2 = accountService.getAccountByToken(account.getToken());
        if(account2!=null){
            return account2;
        }
        else return null;
    }

}
