package com.training.service.Impl;

import com.training.domain.Route;
import com.training.repository.RouteRepository;
import com.training.response.ResponseResult;
import com.training.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public ResponseResult findAllRoute() {
        return new ResponseResult(routeRepository.findAll());
    }

    @Override
    public ResponseResult findRouteById(Long id) {
        return new ResponseResult(routeRepository.findById(id).get()) ;
    }

    @Override
    public ResponseResult findRouteByCarId(Long carid) {
        return new ResponseResult(routeRepository.findRouteByCarId(carid));
    }

    @Override
    public ResponseResult findRouteByEmployeeId(Long employeeId) {
        return new ResponseResult(routeRepository.findRouteByEmployeeId(employeeId));
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
    public void deleteRouteByEmployeeId(Long employeeId) {
        for (Route r : routeRepository.findRouteByEmployeeId(employeeId)) routeRepository.delete(r);
    }

    @Override
    public ResponseResult saveRoute(Route route) {
        Route r = routeRepository.save(route);
        return new ResponseResult(r);
    }

    @Override
    public ResponseResult updateRoute(Route route) {
        return new ResponseResult(routeRepository.save(route));
    }

    @Override
    public ResponseResult updateStatusOfRouteById(Long id, int st) {
        Route r = routeRepository.findById(id).get();
        r.setStatus(st);
        return new ResponseResult(routeRepository.save(r));
    }
}
