package com.training.service;

import com.training.domain.Route;
import com.training.domain.SecRoute;
import com.training.model.SecRouteModel;
import com.training.response.ResponseResult;

import java.util.List;

public interface SecRouteService {
    ResponseResult findAllSecRoute();
    ResponseResult findSecRouteById(Long id);
    ResponseResult findSecRouteByRouteId(Long routeid);
    void deleteSecRoute(SecRoute secroute);
    void deleteSecRouteById(Long id);
    void deleteSecRouteByRouteId(Long routeid);
    ResponseResult saveSecRoute(SecRoute secroute);
    ResponseResult updateSecRoute(SecRoute secroute);

    ResponseResult findRemainSecRouteByRouteId(Long routeid, Long userId);
    //根据routeId返回集合settlement的数据
    List<SecRouteModel> findFDSecRouteByRouteId(Long id);
}
