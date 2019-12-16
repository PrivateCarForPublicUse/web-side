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
        List<UserRate> userRates = userRateRepository.findAll();
        if (userRates.size() == 0)
            return new ResponseResult(500,"不存在任何评价!");
        return new ResponseResult(userRates);
    }

    @Override
    public ResponseResult save(UserRate userRate){
        try {
            UserRate u = userRateRepository.save(userRate);
            return new ResponseResult(u);
        }
        catch (Exception e){
            return new ResponseResult(501,"新建失败!");
        }
    }

    @Override
    public ResponseResult update(UserRate userRate) {
        try {
            UserRate u = userRateRepository.save(userRate);
            return new ResponseResult(u);
        }
        catch (Exception e){
            return new ResponseResult(504,"更新失败!");
        }
    }

    @Override
    public ResponseResult findByUserId(Long userId) {
        List<UserRate> userRates = userRateRepository.findByUserId(userId);
        if (userRates.size() == 0)
            return new ResponseResult(502,"该用户没有发表过任何评价!");
        return new ResponseResult(userRates);
    }

    @Override
    public ResponseResult findByEvaluateeId(Long evaluateeId) {
        List<UserRate> userRates = userRateRepository.findByEvaluateeId(evaluateeId);
        if (userRates.size() == 0)
            return new ResponseResult(503,"该用户没有受到过任何评价!");
        return new ResponseResult(userRates);
    }
}
