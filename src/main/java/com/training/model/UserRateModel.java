package com.training.model;

import com.training.domain.User;
import com.training.domain.UserRate;

public class UserRateModel {
    private User user;
    private UserRate userRate;
    public UserRateModel(){}

    public UserRateModel(User user, UserRate userRate) {
        this.user = user;
        this.userRate = userRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserRate getUserRate() {
        return userRate;
    }

    public void setUserRate(UserRate userRate) {
        this.userRate = userRate;
    }
}
