package com.training.service.Impl;

import com.training.domain.Reimburse;
import com.training.domain.Route;
import com.training.domain.Settlement;
import com.training.model.ReimburseListOfUser;
import com.training.model.ReimburseModel;
import com.training.repository.*;
import com.training.response.ResponseResult;
import com.training.service.ReimburseService;
import com.training.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * by Huang
 */
@Service
public class ReimburseServiceImpl implements ReimburseService {
    @Autowired
    RouteService routeService;
    @Autowired
    ReimburseRepository reimburseRepository;
    @Autowired
    SettlementRepository settlementRepository;

    @Autowired
    SecRouteRepository secRouteRepository;
    @Override
    public ResponseResult getReimburses(){
        List<Reimburse> reimburses = reimburseRepository.findAll();
        if (reimburses.size() == 0)
            return new ResponseResult(500,"不存在任何记录!");
        return new ResponseResult(reimburses);
    }

    @Override
    public ResponseResult getReimbursesByStatus(int isReimburse){
        List<Reimburse> reimburses = reimburseRepository.findByStatus(isReimburse);
        if (reimburses.size() == 0){
            switch (isReimburse){
                case 0:return new ResponseResult(501,"没有未报销的行程!");
                case 1:return new ResponseResult(502,"没有已报销的行程!");
                case -1:return new ResponseResult(503,"没有报销失败的行程!");
                case 2:return new ResponseResult(509,"没有待审核的行程!");
                default:return new ResponseResult(504,"无效的识别码!");
            }
        }
        return new ResponseResult(reimburses);
    }

    @Override
    public ResponseResult getReimburseById(Long id) {
        try {
            Reimburse reimburse = reimburseRepository.findById(id).get();
            return new ResponseResult(reimburse);
        }
        catch (Exception e) {
            return new ResponseResult(505, "id不存在!");
        }
    }

    @Override
    public ResponseResult getReimburseByRouteId(Long routeId) {
        Reimburse reimburse = reimburseRepository.findByRouteId(routeId);
        if (reimburse==null)
            return new ResponseResult(506, "routeId不存在!");
        return new ResponseResult(reimburse);
    }

    @Override
    public ResponseResult save(Reimburse reimburse){
        try {
            Reimburse r = reimburseRepository.save(reimburse);
            return new ResponseResult(r);
        }
        catch (Exception e){
            return new ResponseResult(508,"新建失败!");
        }
    }

    @Override
    public ResponseResult update(Reimburse reimburse) {
        try {
            Reimburse r = reimburseRepository.save(reimburse);
            return new ResponseResult(r);
        }
        catch (Exception e){
            return new ResponseResult(507,"更新失败!");
        }
    }

    @Override
    public ResponseResult applyReimburse(List<Long> routeIds) {
        try {
            for(Long routeId : routeIds){
                Reimburse reimburse = reimburseRepository.findById(routeId).get();
                reimburse.setIsReimburse(2);
                reimburseRepository.save(reimburse);
            }
            return new ResponseResult(200,"更新成功!");
        }
        catch (Exception e){
            return new ResponseResult(507,"更新失败!");
        }
    }

    @Override
    public ResponseResult getReimburse(Long userId) {
        ResponseResult routeByUserId = routeService.findRouteByUserId(userId);
        List<Route> list = (List<Route>)routeByUserId.getData();
        List<ReimburseListOfUser> dtoList=new ArrayList<>();
        for (Route route : list) {
            ReimburseListOfUser reimburseListOfUser = new ReimburseListOfUser();
            this.searchStartAndEndPlaceByRouteId(route.getId(),reimburseListOfUser);
        }
        return null;
    }

    private void searchStartAndEndPlaceByRouteId(Long id, ReimburseListOfUser reimburseListOfUser) {
        List<Settlement> settlementByRouteId = settlementRepository.findSettlementByRouteId(id);
        Collections.sort(settlementByRouteId);
     //   reimburseListOfUser.setStartPlace(settlementByRouteId.get().s);
    }

    //包装
    private ReimburseModel packReimburseModel(Reimburse r){
        if(r==null)return null;
        return new ReimburseModel(r,routeService.findFDRouteById(r.getRouteId()));
    }
    private List<ReimburseModel> packReimburseModels(List<Reimburse>reimburses){
        if(reimburses==null)return null;
        List<ReimburseModel> reimburseModels=new ArrayList<>();
        for(Reimburse r:reimburses){
            reimburseModels.add(this.packReimburseModel(r));
        }
        return reimburseModels;
    }
}
