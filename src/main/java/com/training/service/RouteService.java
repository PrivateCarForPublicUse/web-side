package com.training.service;

import com.training.domain.Route;
import com.training.response.ResponseResult;

public interface RouteService {
    ResponseResult findAllRoute();
    ResponseResult findRouteById(Long id);
    ResponseResult findRouteByCarId(Long carid);
    ResponseResult findRouteByEmployeeId(Long employeeId);
    void deleteRoute(Route route);
    void deleteRouteById(Long id);
    void deleteRouteByCarId(Long carid);
    void deleteRouteByEmployeeId(Long employeeId);
    ResponseResult saveRoute(Route route);
    ResponseResult updateRoute(Route route);
    ResponseResult updateStatusOfRouteById(Long id, int st);
}
