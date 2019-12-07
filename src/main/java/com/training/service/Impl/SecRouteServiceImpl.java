package com.training.service.Impl;

import com.training.domain.Route;
import com.training.domain.SecRoute;
import com.training.repository.SecRouteRepository;
import com.training.response.ResponseResult;
import com.training.service.SecRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecRouteServiceImpl implements SecRouteService {

    @Autowired
    SecRouteRepository secRouteRepository;

    @Override
    public ResponseResult findAllSecRoute() {
        return new ResponseResult(secRouteRepository.findAll());
    }

    @Override
    public ResponseResult findSecRouteById(Long id) {
        return new ResponseResult(secRouteRepository.findById(id).get());
    }

    @Override
    public ResponseResult findSecRouteByRouteId(Long routeid) {
        return new ResponseResult(secRouteRepository.findSecRouteByRouteId(routeid));
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
        SecRoute sec = secRouteRepository.save(secroute);
        return new ResponseResult(sec);
    }

    @Override
    public ResponseResult updateSecRoute(SecRoute secroute) {
        return new ResponseResult(secRouteRepository.save(secroute));
    }
}
