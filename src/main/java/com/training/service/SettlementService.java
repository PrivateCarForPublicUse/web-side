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
    //返回所有详细数据
    ResponseResult findFDSettlements();
    //根据审核状态和用户Id返回包含所有信息的路程
    ResponseResult findSettlementByUserIdAndStatus(Long userId);
}
