package com.training.service;

import com.training.domain.Route;
import com.training.domain.SecRoute;
import com.training.response.ResponseResult;

public interface SecRouteService {
    ResponseResult findAllSecRoute();
    ResponseResult findSecRouteById(Long id);
    ResponseResult findSecRouteByRouteId(Long routeid);
    void deleteSecRoute(SecRoute secroute);
    void deleteSecRouteById(Long id);
    void deleteSecRouteByRouteId(Long routeid);
    ResponseResult saveSecRoute(SecRoute secroute);
    ResponseResult updateSecRoute(SecRoute secroute);
}
