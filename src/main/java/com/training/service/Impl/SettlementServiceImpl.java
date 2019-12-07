package com.training.service.Impl;

import com.training.domain.Settlement;
import com.training.repository.SettlementRepository;
import com.training.response.ResponseResult;
import com.training.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    SettlementRepository settlementRepository;

    @Override
    public ResponseResult findAllSettlement() {
        return new ResponseResult(settlementRepository.findAll());
    }

    @Override
    public ResponseResult findSettlementById(Long id) {
        return new ResponseResult(settlementRepository.findById(id).get());
    }

    @Override
    public ResponseResult findSettlementByRouteId(Long routeid) {
        return new ResponseResult(settlementRepository.findSettlementByRouteId(routeid));
    }

    @Override
    public ResponseResult findSettlementBySecRouteId(Long secrouteid) {
        return new ResponseResult(settlementRepository.findSettlementBySecRouteId(secrouteid));
    }

    @Override
    public ResponseResult findSettlementByEmployeeId(Long employeeId) {
        return new ResponseResult(settlementRepository.findSettlementByEmployeeId(employeeId));
    }

    @Override
    public ResponseResult saveSettlement(Settlement settlement) {
        Settlement s = settlementRepository.save(settlement);
        return new ResponseResult(s);
    }

    @Override
    public ResponseResult updateSettlement(Settlement settlement) {
        return new ResponseResult(settlementRepository.save(settlement));
    }
}
