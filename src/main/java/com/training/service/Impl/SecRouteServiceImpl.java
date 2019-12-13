package com.training.service.Impl;

import com.training.domain.Route;
import com.training.domain.SecRoute;
import com.training.repository.SecRouteRepository;
import com.training.response.ResponseResult;
import com.training.service.SecRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecRouteServiceImpl implements SecRouteService {

    @Autowired
    SecRouteRepository secRouteRepository;

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
}
