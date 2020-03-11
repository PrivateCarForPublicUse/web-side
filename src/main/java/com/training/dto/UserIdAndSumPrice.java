package com.training.dto;

import java.math.BigInteger;

public class UserIdAndSumPrice {


    BigInteger userId;
    Double sum;

    public UserIdAndSumPrice() {
    }

    public UserIdAndSumPrice(BigInteger userId, Double sum) {
        this.userId = userId;
        this.sum = sum;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
