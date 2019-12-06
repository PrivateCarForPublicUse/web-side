package com.training.service.Impl;

import com.training.domain.UserRate;
import com.training.repository.UserRateRepository;
import com.training.service.UserRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by Huang
 */
@Service
public class UserRateServiceImpl implements UserRateService {
    @Autowired
    UserRateRepository userRateRepository;

    @Override
    public List<UserRate> getUserRates(){
        return userRateRepository.findAll();
    }

    @Override
    public UserRate save(UserRate userRate){
        return userRateRepository.save(userRate);
    }

    @Override
    public UserRate update(UserRate userRate) {
        return userRateRepository.save(userRate);
    }

}
