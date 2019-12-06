package com.training.service.Impl;

import com.training.domain.Reimburse;
import com.training.repository.ReimburseRepository;
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
    public List<Reimburse> getReimburses(){
        return reimburseRepository.findAll();
    }

    @Override
    public Reimburse getReimburseById(Long id) {
        return reimburseRepository.findById(id).get();
    }

    @Override
    public Reimburse save(Reimburse reimburse){
        return reimburseRepository.save(reimburse);
    }

    @Override
    public Reimburse update(Reimburse reimburse) {
        return reimburseRepository.save(reimburse);
    }

}
