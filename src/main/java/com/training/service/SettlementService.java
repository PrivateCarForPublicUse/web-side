package com.training.service;

import com.training.domain.Settlement;
import com.training.response.ResponseResult;

public interface SettlementService {
    ResponseResult findAllSettlement();
    ResponseResult findSettlementById(Long id);
    ResponseResult findSettlementByRouteId(Long routeid);
    ResponseResult findSettlementBySecRouteId(Long secrouteid);
    ResponseResult findSettlementByUserId(Long userId);
    ResponseResult saveSettlement(Settlement settlement);
    ResponseResult updateSettlement(Settlement settlement);
}
