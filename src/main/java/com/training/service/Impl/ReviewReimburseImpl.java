package com.training.service.Impl;

import com.training.domain.*;
import com.training.dto.ReviewReimburseDTO;
import com.training.repository.RouteRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import com.training.service.ReviewReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewReimburseImpl implements ReviewReimburseService {



    @Autowired
    AccountService accountService;

    @Autowired
    RouteRepository routeRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseResult acceptReimburse(ReviewReimburseDTO reviewReimburseDTO, Account account) {

        if(account.getFlag()==1){
            Master master =(Master) accountService.getMasterByAccountId(account.getId()).getData();
            if(master.getCompanyId().equals(getCompanyId(reviewReimburseDTO))){
                List<Settlement> list= routeRepository.findSettlementById(reviewReimburseDTO.getRouteId());
                Double sum=0.0;
                for (Settlement settlement : list) {
                    sum+=settlement.getDrivingCost();
                }
                return null;
            }
            return new ResponseResult(500,"别想报销到别的公司去！",null);
        }
        else return new ResponseResult(500,"权限不足！",null);
    }

    @Override
    public Long getCompanyId(ReviewReimburseDTO reviewReimburseDTO) {
        Route route = routeRepository.findById(reviewReimburseDTO.getRouteId()).get();
        User user = userRepository.findById(route.getUserId()).get();
        return user.getCompanyId();
    }
}
