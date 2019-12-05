package com.training.service;

import com.training.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();
    Account getAccountById(Long id);
    Account getAccountByToken(String s);
    Account save(Account user);
    Account update(Account user);
    Account delete(Long id);
}
