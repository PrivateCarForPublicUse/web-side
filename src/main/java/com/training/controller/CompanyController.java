package com.training.controller;

import com.training.response.ResponseResult;
import com.training.service.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value="/company",tags="公司信息接口")
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @ResponseBody
    @GetMapping("/all")
    public ResponseResult getAllCompanies(){
        return companyService.getAllCompanies();
    }
}
