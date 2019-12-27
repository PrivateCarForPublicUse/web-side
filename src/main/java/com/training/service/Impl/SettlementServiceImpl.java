package com.training.service.Impl;

import com.training.domain.Settlement;
import com.training.model.RouteModel;
import com.training.model.SettlementModel;
import com.training.model.SettlementModelTwo;
import com.training.repository.RouteRepository;
import com.training.repository.SettlementRepository;
import com.training.response.ResponseResult;
import com.training.service.RouteService;
import com.training.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SettlementServiceImpl implements SettlementService {

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    SettlementRepository settlementRepository;
    @Autowired
    RouteService routeService;

    @Override
    public ResponseResult findAllSettlement() {
        List<Settlement> settlements = settlementRepository.findAll();
        if (settlements.size() == 0)
            return new ResponseResult(500,"结算不存在!");
        return new ResponseResult(settlements);
    }

    @Override
    public ResponseResult findSettlementById(Long id) {
        Settlement settlement = settlementRepository.findById(id).get();
        if (settlement == null)
            return new ResponseResult(501,"id不存在!");
        return new ResponseResult(settlement);
    }

    @Override
    public ResponseResult findSettlementByRouteId(Long routeid) {
        List<Settlement> settlements = settlementRepository.findSettlementByRouteId(routeid);
        if (settlements.size() == 0)
            return new ResponseResult(502,"routeid不存在!");
        return new ResponseResult(settlements);
    }

    @Override
    public ResponseResult findSettlementBySecRouteId(Long secrouteid) {
        List<Settlement> settlements = settlementRepository.findSettlementBySecRouteId(secrouteid);
        if (settlements.size() == 0)
            return new ResponseResult(504,"secrouteid不存在!");
        return new ResponseResult(settlements);
    }


    @Override
    public ResponseResult saveSettlement(Settlement settlement) {
        try {
            Settlement s = settlementRepository.save(settlement);
            return new ResponseResult(s);
        }
        catch (Exception e){
            return new ResponseResult(505,"插入失败!");
        }
    }

    @Override
    public ResponseResult updateSettlement(Settlement settlement) {
        try {
            Settlement s = settlementRepository.save(settlement);
            return new ResponseResult(s);
        }
        catch (Exception e){
            return new ResponseResult(506,"更新失败!");
        }
    }

    //返回所有数据
    @Override
    public ResponseResult findFDSettlements() {
        return new ResponseResult(packSettlementModels(settlementRepository.findAll()));
    }

    //包装
    private SettlementModel packSettlementModel(Settlement settlement){
        if(settlement==null)return null;
        return new SettlementModel(settlement,routeService.findFDRouteById(settlement.getRouteId()));
    }
    private List<SettlementModel> packSettlementModels(List<Settlement>settlements){
        if(settlements==null)return null;
        List<SettlementModel> settlementModels=new ArrayList<>();
        for(Settlement s:settlements){
            settlementModels.add(this.packSettlementModel(s));
        }
        return settlementModels;
    }
}
