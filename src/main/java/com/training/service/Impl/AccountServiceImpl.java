package com.training.service.Impl;

import com.training.domain.Account;
import com.training.repository.AccountRepository;
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
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account getAccountByToken(String token) {
        return accountRepository.findByToken(token);
    }

    @Override
    public Account save(Account user) {
        user.setToken(UUID.randomUUID().toString());
        return accountRepository.save(user);
    }

    @Override
    public Account update(Account user) {
        return accountRepository.save(user);
    }

    @Override
    public Account delete(Long id) {
        Account account= accountRepository.findById(id).get();
        accountRepository.deleteById(id);
        return account;
    }
}
