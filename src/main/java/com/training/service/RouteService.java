package com.training.service;

import com.training.domain.Route;
import com.training.model.RouteModel;
import com.training.response.ResponseResult;

import java.util.List;

public interface RouteService {
    ResponseResult findAllRoute();
    ResponseResult findRouteById(Long id);
    ResponseResult findRouteByCarId(Long carid);
    ResponseResult findRouteByUserId(Long userId);
    void deleteRoute(Route route);
    void deleteRouteById(Long id);
    void deleteRouteByCarId(Long carid);
    void deleteRouteByUserId(Long userId);
    ResponseResult saveRoute(Route route);
    ResponseResult updateRoute(Route route);
    ResponseResult updateStatusOfRouteById(Long id, int st);
    // 根据审核状态返回行程
    ResponseResult findRoutesByStatus(int status);
    // 获取包含所有信息的所有路程
    ResponseResult findFDRoutes();
    // 根据id获取包含所有信息的路程
    RouteModel findFDRouteById(Long id);
    //根据报销状态返回行程
    ResponseResult findFDRoutesByIsReimburse(int is);
}
