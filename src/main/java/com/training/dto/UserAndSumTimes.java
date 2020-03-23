package com.training.dto;

import com.training.domain.User;

import java.math.BigInteger;

public class UserAndSumTimes {
    User user;
    BigInteger Times;

    public UserAndSumTimes(User user, BigInteger times) {
        this.user = user;
        Times = times;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigInteger getTimes() {
        return Times;
    }

    public void setTimes(BigInteger times) {
        Times = times;
    }
}
