package com.training.dto;

import com.training.domain.Account;
import com.training.domain.User;

public class LoginDTO {
    private Account account;
    private User user;

    public LoginDTO(Account account, User user) {
        this.account = account;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
