package com.training.service.Impl;

import com.training.domain.Route;
import com.training.domain.SecRoute;
import com.training.domain.Settlement;
import com.training.model.SecRouteModel;
import com.training.repository.RouteRepository;
import com.training.repository.SecRouteRepository;
import com.training.repository.SettlementRepository;
import com.training.response.ResponseResult;
import com.training.service.SecRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecRouteServiceImpl implements SecRouteService {

    @Autowired
    SecRouteRepository secRouteRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    SettlementRepository settlementRepository;

    @Override
    public ResponseResult findAllSecRoute() {
        List<SecRoute> secRoutes = secRouteRepository.findAll();
        if (secRoutes.size() == 0)
            return new ResponseResult(500,"段行程不存在!");
        return new ResponseResult(secRoutes);
    }

    @Override
    public ResponseResult findSecRouteById(Long id) {
        SecRoute secRoute = secRouteRepository.findById(id).get();
        if (secRoute == null)
            return new ResponseResult(501,"id不存在!");
        return new ResponseResult(secRoute);
    }

    @Override
    public ResponseResult findSecRouteByRouteId(Long routeid) {
        List<SecRoute> secRoutes = secRouteRepository.findSecRouteByRouteId(routeid);
        if (secRoutes.size() == 0)
            return new ResponseResult(502,"routeid不存在!");
        return new ResponseResult(secRoutes);
    }

    @Override
    public void deleteSecRoute(SecRoute secroute) {
        secRouteRepository.delete(secroute);
    }

    @Override
    public void deleteSecRouteById(Long id) {
        secRouteRepository.deleteById(id);
    }

    @Override
    public void deleteSecRouteByRouteId(Long routeid) {
        secRouteRepository.deleteSecRouteByRouteId(routeid);
    }

    @Override
    public ResponseResult saveSecRoute(SecRoute secroute) {
        try {
            SecRoute sec = secRouteRepository.save(secroute);
            return new ResponseResult(sec);
        }
        catch (Exception e){
            return new ResponseResult(503,"插入失败!");
        }
    }

    @Override
    public ResponseResult updateSecRoute(SecRoute secroute) {
        try {
            SecRoute sec = secRouteRepository.save(secroute);
            return new ResponseResult(sec);
        }
        catch (Exception e){
            return new ResponseResult(504,"更新失败!");
        }
    }

    @Override
    public List<SecRouteModel> findFDSecRouteByRouteId(Long id) {
        return this.packSecRouteModels(secRouteRepository.findSecRouteByRouteId(id));
    }

    //根据id返回SecRouteModel
//    public List<SecRouteModel> ()

    //和settlement包装
    private SecRouteModel packSecRouteModel(SecRoute secRoute){
        if(secRoute==null)return null;
        List<Settlement> settlement = settlementRepository.findSettlementBySecRouteId(secRoute.getId());
        if(settlement!=null&&settlement.size()>0)
            return new SecRouteModel(secRoute,settlement.get(0));
        else return new SecRouteModel(secRoute,null);
    }
    private List<SecRouteModel> packSecRouteModels(List<SecRoute>secRoutes){
        if(secRoutes==null)return null;
        List<SecRouteModel>secRouteModels=new ArrayList<>();
        for(SecRoute secRoute:secRoutes){
            secRouteModels.add(this.packSecRouteModel(secRoute));
        }
        return secRouteModels;
    }

    @Override
    public ResponseResult findRemainSecRouteByRouteId(Long routeid, Long userId) {
        Route route = routeRepository.findRouteById(routeid);
        if (route.getUserId().longValue() != userId) {
            return new ResponseResult(507, "没有操作权限!");
        }
        List<SecRoute> secRoutes = secRouteRepository.findSecRouteByRouteId(routeid);
        List<SecRoute> result = new ArrayList<>();
        for (SecRoute secRoute : secRoutes) {
            if (settlementRepository.findSettlementBySecRouteId(secRoute.getId()).size() == 0)
                result.add(secRoute);
        }
        return new ResponseResult(result);
    }
}
