package com.training.service.Impl;

import com.training.domain.Reimburse;
import com.training.repository.ReimburseRepository;
import com.training.response.ResponseResult;
import com.training.service.ReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * by Huang
 */
@Service
public class ReimburseServiceImpl implements ReimburseService {
    @Autowired
    ReimburseRepository reimburseRepository;

    @Override
    public ResponseResult getReimburses(){
        return new ResponseResult(reimburseRepository.findAll());
    }

    @Override
    public ResponseResult getReimbursesByStatus(int isReimburse){
        return new ResponseResult(reimburseRepository.findByStatus(isReimburse));
    }

    @Override
    public ResponseResult getReimburseById(Long id) {
        Reimburse reimburse=reimburseRepository.findById(id).get();
        if (reimburse!=null)
            return new ResponseResult(reimburse);
        else return new ResponseResult(404,"can't find the ID");
    }

    @Override
    public ResponseResult getReimburseByRouteId(Long routeId) {
        Reimburse reimburse=reimburseRepository.findByRouteId(routeId);
        if (reimburse!=null)
            return new ResponseResult(reimburse);
        else return new ResponseResult(404,"can't find the routeID");
    }

    @Override
    public ResponseResult save(Reimburse reimburse){
        return new ResponseResult(reimburseRepository.save(reimburse));
    }

    @Override
    public ResponseResult update(Reimburse reimburse) {
        return new ResponseResult(reimburseRepository.save(reimburse));
    }

}
