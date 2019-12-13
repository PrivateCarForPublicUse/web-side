package com.training.service;

import com.training.domain.Route;
import com.training.response.ResponseResult;

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
}
