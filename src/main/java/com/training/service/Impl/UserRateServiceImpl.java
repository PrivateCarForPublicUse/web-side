package com.training.service.Impl;

import com.training.domain.User;
import com.training.domain.UserRate;
import com.training.model.UserRateModel;
import com.training.repository.UserRateRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.UserRateService;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
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
    @Autowired
    UserRepository userRepository;

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
//            int count = userRateRepository.findByEvaluateeId(userRate.getEvaluateeId()).size();
//            User user = (User)userService.getUserById(userRate.getEvaluateeId()).getData();
//            user.setStarLevel((user.getStarLevel()*(count-1) + userRate.getRate())/count);
            // 原代码修改为根据当前的评分重新计算
            List<UserRate> list=userRateRepository.findByEvaluateeId(userRate.getEvaluateeId());
            double count=0;
            for(UserRate ur : list){
                count+=ur.getRate();
            }
            User user = (User)userService.getUserById(userRate.getEvaluateeId()).getData();
            user.setStarLevel(count/list.size());
            userService.save(user);
            return new ResponseResult(u);
        }
        catch (Exception e){
            return new ResponseResult(501,"新建失败!");
        }
    }

//    @Override
//    public ResponseResult update(UserRate userRate) {
//        try {
//            UserRate u = userRateRepository.save(userRate);
//            return new ResponseResult(u);
//        }
//        catch (Exception e){
//            return new ResponseResult(504,"更新失败!");
//        }
//    }

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

    @Override
    public ResponseResult findByEvaluateeId2(Long evaluateeId) {
        List<UserRate> userRates = userRateRepository.findByEvaluateeId(evaluateeId);
        List<UserRateModel> userRateModelList=new ArrayList<>();
        for(UserRate userRate: userRates){
            userRateModelList.add(new UserRateModel(userRepository.findUserById(userRate.getUserId()),userRate));
        }
        return new ResponseResult(userRateModelList);
    }
}
