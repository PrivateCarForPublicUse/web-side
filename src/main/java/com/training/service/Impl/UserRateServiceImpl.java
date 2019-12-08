package com.training.service.Impl;

import com.training.domain.UserRate;
import com.training.repository.UserRateRepository;
import com.training.response.ResponseResult;
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
    public ResponseResult getUserRates(){
        return new ResponseResult(userRateRepository.findAll());
    }

    @Override
    public ResponseResult save(UserRate userRate){
        return new ResponseResult(userRateRepository.save(userRate));
    }

    @Override
    public ResponseResult update(UserRate userRate) {
        return new ResponseResult(userRateRepository.save(userRate));
    }

    @Override
    public ResponseResult findByUserId(Long userId) {
        return new ResponseResult(userRateRepository.findByUserId(userId));
    }

    @Override
    public ResponseResult findByEvaluateeId(Long evaluateeId) {
        return new ResponseResult(userRateRepository.findByEvaluateeId(evaluateeId));
    }
}
