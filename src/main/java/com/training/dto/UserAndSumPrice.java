package com.training.dto;

import com.training.domain.User;

import java.math.BigInteger;

public class UserAndSumPrice {


    User user;
    Double sum;

    public UserAndSumPrice() {
    }

    public UserAndSumPrice(User user, Double sum) {
        this.user = user;
        this.sum = sum;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
