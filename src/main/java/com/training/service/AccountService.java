package com.training.service;

import com.training.domain.Account;
import com.training.response.ResponseResult;

import java.util.List;

public interface AccountService {
    ResponseResult getAccounts();
    ResponseResult getAccountById(Long id);
    ResponseResult getAccountByToken(String s);
    ResponseResult save(Account user);
    ResponseResult update(Account user);
    ResponseResult delete(Long id);

   // ResponseResult getAccountByUserId(Long id);
}
