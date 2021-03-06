package com.training.service;

import com.training.domain.Account;
import com.training.dto.LoginByMasterNameDTO;
import com.training.dto.LoginByUserNameDTO;
import com.training.dto.LoginDTO;
import com.training.response.ResponseResult;

import javax.servlet.http.HttpServletRequest;

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
    // 由token返回使用者信息，请勿修改 by pja
    ResponseResult getInfo(HttpServletRequest request,String token);
}
