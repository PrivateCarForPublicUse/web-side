package com.training.service.Impl;

import com.training.domain.*;
import com.training.dto.ReviewReimburseDTO;
import com.training.repository.RouteRepository;
import com.training.repository.SettlementRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import com.training.service.ReviewReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ReviewReimburseImpl implements ReviewReimburseService {

    @Autowired
    AccountService accountService;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SettlementRepository settlementRepository;
    @Override
    public ResponseResult acceptReimburse(ReviewReimburseDTO reviewReimburseDTO, Account account) {

        if(account.getFlag()==1){
            Master master =(Master) accountService.getMasterByAccountId(account.getId()).getData();
            if(master.getCompanyId().equals(getCompanyId(reviewReimburseDTO))){
                List<Settlement> list= settlementRepository.findSettlementByRouteId(reviewReimburseDTO.getRouteId());
                Double sum=0.0;
                for (Settlement settlement : list) {
                    settlement.setDrivingCost(settlement.getDrivingCost());
                    sum+=settlement.getDrivingCost();
                }
                Route route= routeRepository.findById(reviewReimburseDTO.getRouteId()).get();
                route.setIsReimburse(1);
                routeRepository.save(route);
                User user = userRepository.findById(route.getUserId()).get();
                user.setRemainMoney(user.getRemainMoney()+sum);
                userRepository.save(user);
                return new ResponseResult(200,"报销成功！",null);
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
