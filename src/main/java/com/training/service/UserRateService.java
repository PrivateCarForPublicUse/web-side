package com.training.service;

import com.training.domain.UserRate;
import com.training.response.ResponseResult;

import java.util.List;

/**
 * by Huang
 */
public interface UserRateService {
    ResponseResult getUserRates(Long companyId);
    ResponseResult save(UserRate userRate);
//    ResponseResult update(UserRate userRate);
    ResponseResult findByUserId(Long userId);
    ResponseResult findByEvaluateeId(Long valuateeId);
    ResponseResult findByEvaluateeId2(Long valuateeId);
}
