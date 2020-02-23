package com.training.service;

import com.training.domain.Reimburse;
import com.training.response.ResponseResult;

import java.util.List;

/**
 * by Huang
 */
public interface ReimburseService {
    ResponseResult getReimburses();
    ResponseResult getReimbursesByStatus(int isReimburse);
    ResponseResult getReimburseById(Long id);
    ResponseResult getReimburseByRouteId(Long routeId);
    ResponseResult save(Reimburse reimburse);
    ResponseResult update(Reimburse reimburse);
    ResponseResult applyReimburse(List<Long> routeIds);
    ResponseResult getReimburse(Long userId);
    ResponseResult GetReimburseStatistic(Long companyId);

    ResponseResult GetReimburseStatistic_person(Long id);
}
