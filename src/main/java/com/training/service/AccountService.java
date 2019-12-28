package com.training.service;

import com.training.domain.Account;
import com.training.dto.LoginByMasterNameDTO;
import com.training.dto.LoginByUserNameDTO;
import com.training.dto.LoginDTO;
import com.training.response.ResponseResult;

public interface AccountService {
    ResponseResult getAccounts();
    ResponseResult getAccountById(Long id);
    ResponseResult getAccountByToken(String s);
    ResponseResult save(Account user);
    ResponseResult update(Account user);
    ResponseResult delete(Long id);
    LoginDTO loginByUserName(LoginByUserNameDTO loginByUserNameDTO);
   // ResponseResult getAccountByUserId(Long id);
    ResponseResult getMasterByAccountId(Long id);

    LoginDTO loginByMasterName(LoginByMasterNameDTO loginByMasterNameDTO);
}
