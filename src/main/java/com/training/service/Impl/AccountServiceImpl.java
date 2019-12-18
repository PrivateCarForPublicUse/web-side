package com.training.service.Impl;

import com.training.domain.Account;
import com.training.repository.AccountRepository;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ResponseResult getAccounts() {
        return new ResponseResult(accountRepository.findAll());
    }

    @Override
    public ResponseResult getAccountById(Long id) {
        return new ResponseResult(accountRepository.findById(id).get());
    }

    @Override
    public ResponseResult getAccountByToken(String token) {
        return new ResponseResult(accountRepository.findByToken(token));
    }

    @Override
    public ResponseResult save(Account user) {
        user.setToken(UUID.randomUUID().toString());
        return new ResponseResult(accountRepository.save(user));
    }

    @Override
    public ResponseResult update(Account user) {
        return new ResponseResult(accountRepository.save(user));
    }

    @Override
    public ResponseResult delete(Long id) {
        Account account= accountRepository.findById(id).get();
        accountRepository.deleteById(id);
        return new ResponseResult(account);
    }
}
