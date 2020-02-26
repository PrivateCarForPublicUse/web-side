package com.training.service.Impl;

import com.training.repository.CompanyRepository;
import com.training.response.ResponseResult;
import com.training.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public ResponseResult getAllCompanies() {
        return new ResponseResult(200,"ok",companyRepository.findAll());
    }
}
