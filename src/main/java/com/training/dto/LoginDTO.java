package com.training.dto;

import com.training.domain.Account;
import com.training.domain.User;

public class LoginDTO {
    private Account account;
    private Object userOrMaster;

    public LoginDTO(Account account, Object userOrMaster) {
        this.account = account;
        this.userOrMaster = userOrMaster;
    }

    public Object getUserOrMaster() {
        return userOrMaster;
    }

    public void setUserOrMaster(User userOrMaster) {
        this.userOrMaster = userOrMaster;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
