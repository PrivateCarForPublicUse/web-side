package com.training.dto;

import java.math.BigInteger;

public class UserIdAndSumTimes {
    BigInteger UserId;
    BigInteger Times;

    public UserIdAndSumTimes(BigInteger userId, BigInteger times) {
        UserId = userId;
        Times = times;
    }

    public BigInteger getUserId() {
        return UserId;
    }

    public void setUserId(BigInteger userId) {
        UserId = userId;
    }

    public BigInteger getTimes() {
        return Times;
    }

    public void setTimes(BigInteger times) {
        Times = times;
    }
}
