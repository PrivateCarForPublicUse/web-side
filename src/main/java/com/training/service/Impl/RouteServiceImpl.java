package com.training.service.Impl;

import com.training.domain.Master;
import com.training.domain.Route;
import com.training.domain.SecRoute;
import com.training.domain.Settlement;
import com.training.model.*;
import com.training.repository.CarRepository;
import com.training.repository.RouteRepository;
import com.training.repository.SecRouteRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.MasterService;
import com.training.service.RouteService;
import com.training.service.SecRouteService;
import com.training.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    SecRouteRepository secRouteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;
    @Autowired
    SecRouteService secRouteService;
    @Autowired
    MasterService masterService;

    @Override
    public ResponseResult findAllRoute() {
        List<Route> Routes = routeRepository.findAll();
        if (Routes.size() == 0)
            return new ResponseResult(500,"行程不存在!");
        return new ResponseResult(Routes);
    }

    @Override
    public ResponseResult findRouteById(Long id) {
        Route route = routeRepository.findById(id).get();
        if (route == null)
            return new ResponseResult(501,"id不存在!");
        return new ResponseResult(this.packRouteModel(route)) ;
    }

    @Override
    public ResponseResult findRouteByCarId(Long carid) {
        List<Route> Routes = routeRepository.findRouteByCarId(carid);
        if (Routes.size() == 0)
            return new ResponseResult(502,"carid不存在!");
        return new ResponseResult(Routes);
    }

    @Override
    public ResponseResult findRouteByUserId(Long userId) {
        List<Route> Routes = routeRepository.findRouteByUserId(userId);
        if (Routes.size() == 0)
            return new ResponseResult(503,"userId不存在!");
        return new ResponseResult(Routes);
    }

    @Override
    public void deleteRoute(Route route) {
        routeRepository.delete(route);
    }

    @Override
    public void deleteRouteById(Long id) {
        routeRepository.deleteById(id);
    }

    @Override
    public void deleteRouteByCarId(Long carid) {
        for (Route r : routeRepository.findRouteByCarId(carid)) routeRepository.delete(r);
    }

    @Override
    public void deleteRouteByUserId(Long userId) {
        for (Route r : routeRepository.findRouteByUserId(userId)) routeRepository.delete(r);
    }

    @Override
    public ResponseResult saveRoute(Route route) {
        try {
            Route r = routeRepository.save(route);
            return new ResponseResult(r);
        }
        catch (Exception e){
            return new ResponseResult(504,"插入失败!");
        }
    }

    @Override
    public ResponseResult updateRoute(Route route) {
        try {
            Route r = routeRepository.save(route);
            return new ResponseResult(r);
        }
        catch (Exception e){
            return new ResponseResult(505,"更新失败!");
        }
    }

    @Override
    public ResponseResult updateStatusOfRouteById(Long id, int st) {
        try {
            Route r = routeRepository.findById(id).get();
            r.setStatus(st);
            return new ResponseResult(routeRepository.save(r));
        }
        catch (Exception e){
            return new ResponseResult(505,"更新失败");
        }
    }

    //根据id返回包含所有信息的路程
    @Override
    public RouteModel findFDRouteById(Long id){
        return this.packRouteModel(routeRepository.findRouteById(id));
    }

    //根据userid返回包含所有信息的路程
    @Override
    public RouteModel findFDRouteByUserId(Long userId){
        return this.packRouteModel(routeRepository.findRouteById(userId));
    }

    //根据审核状态和管理员Id返回包含所有信息的路程
    @Override
    public ResponseResult findRoutesByStatus(int status,Long masterId) {
        Master master = (Master) masterService.findMasterById(masterId).getData();
        return new ResponseResult(this.packRouteModels(routeRepository.findRoutesByStatus(status,master.getCompanyId())));
    }

    //获取包含所有数据的所有路程信息
    @Override
    public ResponseResult findFDRoutes(){
        return new ResponseResult(this.packRouteModels(routeRepository.findAll()));
    }

    //根据报销状态返回行程
    @Override
    public ResponseResult findFDRoutesByIsReimburse(int is){
        return new ResponseResult(this.packRouteModels(routeRepository.findRoutesByIsReimburse(is)));
    }

    //包装返回的Route类型r
    private RouteModel packRouteModel(Route r){
        if(r==null)return null;
        return new RouteModel(userRepository.getUserById(r.getUserId()),carRepository.getCarById(r.getCarId()),r,secRouteService.findFDSecRouteByRouteId(r.getId()));
    }
    //包装返回的Route类型routes
    private List<RouteModel> packRouteModels(List<Route>routes){
        if(routes==null)return null;
        List<RouteModel> routeModels=new ArrayList<>();
        for(Route r:routes){
            routeModels.add(this.packRouteModel(r));
        }
        return routeModels;
    }

    @Override
    public ResponseResult findDataByUserIdAndStatus(Long userId) {
        List<Route> routes = routeRepository.findRouteByUserIdAndStatus(userId);
        List<RouteModel> routeModels = packRouteModels(routes);
        List<DataModel> models = new ArrayList<>();
        for (RouteModel routeModel: routeModels){
            DataModel dataModel = new DataModel();
            dataModel.setRoutId(routeModel.getRoute().getId());
            dataModel.setApplyTime(routeModel.getRoute().getApplyTime());
            dataModel.setReason(routeModel.getRoute().getReason());
            dataModel.setIsReimburse(routeModel.getRoute().getIsReimburse());
            Double total_cost = 0.0,total_dist = 0.0;
            List<SecModel> secModels = new ArrayList<>();
            List<SecRouteModel> secRouteModels = routeModel.getSecRoutes();
            for (SecRouteModel secRouteModel: secRouteModels){
                total_cost += secRouteModel.getSettlement().getDrivingCost();
                total_dist += secRouteModel.getSettlement().getDrivingDistance();
                SecModel secModel = new SecModel(secRouteModel.getSecRoute().getId(),secRouteModel.getSecRoute().getOrigin(),secRouteModel.getSecRoute().getDestination(),secRouteModel.getSettlement().getCarStartTime(),secRouteModel.getSettlement().getCarStopTime(),secRouteModel.getSettlement().getDrivingDistance(),secRouteModel.getSettlement().getDrivingCost());
                secModels.add(secModel);
            }
            dataModel.setSecModels(secModels);
            dataModel.setCost(total_cost);
            dataModel.setRoutelength(total_dist);
            models.add(dataModel);
        }
        return new ResponseResult(models);
    }
}
