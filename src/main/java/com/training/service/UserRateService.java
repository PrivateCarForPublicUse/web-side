package com.training.service;

import com.training.domain.UserRate;

import java.util.List;

/**
 * by Huang
 */
public interface UserRateService {
    List<UserRate> getUserRates();
    UserRate save(UserRate userRate);
    UserRate update(UserRate userRate);
}
