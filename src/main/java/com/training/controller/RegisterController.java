package com.training.controller;


import com.training.domain.Account;
import com.training.domain.Company;
import com.training.domain.Master;
import com.training.domain.User;
import com.training.dto.AddMasterDTO;
import com.training.dto.CompanyCityDTO;
import com.training.dto.LoginDTO;
import com.training.dto.RegisterDTO;
import com.training.repository.CompanyRepository;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import com.training.service.MasterService;
import com.training.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api(value="/register",tags="注册接口")
@RequestMapping("/register")
@RestController
public class RegisterController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    MasterService masterService;
    @Autowired
    CompanyRepository companyRepository;

    @PostMapping("/")
    public ResponseResult register(@RequestBody RegisterDTO registerDTO){


        if(userService.getUserByPhone(registerDTO.getPhoneNumber()).getData()!=null){
            return new ResponseResult(500,"手机号已注册",null);
        }

        Account account = new Account();
        account.setPassword(registerDTO.getPassword());
        account.setFlag(0);
        Account account2 = (Account) accountService.save(account).getData();

        User user = new User();
        user.setAccountId(account2.getId());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setCompanyId(registerDTO.getCompanyId());
        user.setWorkNumber(registerDTO.getWorkNumber());
       // user.setDriverLicenseBackUrl(registerDTO.getDriverLicense().getUrl());
        user.setIdCardNumber(registerDTO.getIdCard().getIdNumber());
        user.setDriverLicenseNumber(registerDTO.getDriverLicense().getLicenceId());
        userService.save(user);

        LoginDTO loginDTO = new LoginDTO(account2,user);

        return new ResponseResult(loginDTO);
    }
    @PostMapping("/addMaster")
    public ResponseResult register(@RequestBody AddMasterDTO addMasterDTO, HttpServletRequest request){

        HttpSession session = request.getSession();
        Master masterX = (Master) session.getAttribute("master");
        if(masterX==null||masterX.getIsCompanyMaster()!=1)
            return new ResponseResult(500,"权限不足",null);
        Master master = masterService.getMasterByName(addMasterDTO.getName());
        if(master!=null){
            return new ResponseResult(501,"用户名已注册",null);
        }

        Account account = new Account();
        account.setPassword(addMasterDTO.getPassword());
        account.setFlag(1);
        Account account2 = (Account) accountService.save(account).getData();

        master = new Master();
        master.setAccountId(account2.getId());
        master.setMasterName(addMasterDTO.getName());
        master.setCompanyId(masterX.getCompanyId());
        master.setIsCompanyMaster(0);
        masterService.saveMaster(master);

        LoginDTO loginDTO = new LoginDTO(account2,master);

        return new ResponseResult(loginDTO);
    }

    @GetMapping("/CompanyCity")
    public ResponseResult getCompanyCity(){
        CompanyCityDTO companyCitys = new CompanyCityDTO();
        List<String> list = companyRepository.findCities();
        return new ResponseResult(list);
    }

    @PostMapping("/Companies")
    public ResponseResult getCompaniesBycity(@RequestBody String cityName){
        //CompanyCityDTO companyCitys = new CompanyCityDTO();
        List<Company> list = companyRepository.findCompaniesByCity(cityName);
        return new ResponseResult(list);
    }
}
