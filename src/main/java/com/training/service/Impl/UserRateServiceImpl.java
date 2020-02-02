package com.training.service.Impl;

import com.training.domain.User;
import com.training.domain.UserRate;
import com.training.repository.UserRateRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.UserRateService;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * by Huang
 */
@Service
public class UserRateServiceImpl implements UserRateService {
    @Autowired
    UserRateRepository userRateRepository;
    @Autowired
    UserService userService;

    @Override
    public ResponseResult getUserRates(Long companyId){
        List<UserRate> userRates = userRateRepository.findAllByCompany(companyId);
        if (userRates.size() == 0)
            return new ResponseResult(500,"不存在任何评价!");
        return new ResponseResult(userRates);
    }

    @Override
    public ResponseResult save(UserRate userRate){
        try {
            UserRate u = userRateRepository.save(userRate);
            int count = userRateRepository.findByUserId(userRate.getUserId()).size();
            User user = (User)userService.getUserById(userRate.getUserId()).getData();
            user.setStarLevel((user.getStarLevel() + userRate.getRate())/(count+1));
            userService.save(user);
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
