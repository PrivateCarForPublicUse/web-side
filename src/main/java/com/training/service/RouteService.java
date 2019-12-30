package com.training.service;

import com.training.domain.Route;
import com.training.model.RouteModel;
import com.training.response.ResponseResult;

import java.text.ParseException;
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
    // 根据审核状态和管理员id返回行程
    ResponseResult findRoutesByStatusAndMasterId(int status,Long masterId);

    // 获取包含所有信息的所有路程
    ResponseResult findFDRoutes();
    // 根据id获取包含所有信息的路程
    RouteModel findFDRouteById(Long id);
    // 根据UserId获取包含所有信息的路程
    RouteModel findFDRouteByUserId(Long userId);
    //根据报销状态返回行程
    ResponseResult findFDRoutesByIsReimburse(int is);
    //根据审核状态和用户Id返回包含所有信息的路程
    ResponseResult findDataByUserIdAndStatus(Long userId);
    //开始行程
    ResponseResult startRoute(Long UserId,Long RouteId,Long secRouteId,String trid,String carStartTime);
    //结束行程
    ResponseResult stopRoute(Long UserId,Long RouteId,Long secRouteId,Double plannedDistance,Double actualDistance) throws ParseException;
    //返回当前登录用户的未报销行程
    ResponseResult findUserRouteByStatus(Long userId,int status,int isReimburse);
    //根据行程完成情况和报销情况返回行程
    ResponseResult findRoutesByStatusAndIsReimburse(int status,int isReimburse);
}
