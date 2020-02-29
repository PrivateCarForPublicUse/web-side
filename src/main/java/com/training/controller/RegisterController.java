package com.training.controller;


import com.training.domain.*;
import com.training.dto.AddMasterDTO;
import com.training.dto.CompanyCityDTO;
import com.training.dto.LoginDTO;
import com.training.dto.RegisterDTO;
import com.training.repository.AccountRepository;
import com.training.repository.CarRepository;
import com.training.repository.CompanyRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import com.training.service.MasterService;
import com.training.service.UploadService;
import com.training.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Api(value = "/register", tags = "注册接口")
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
    @Autowired
    UploadService uploadService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CarRepository carRepository;

    @PostMapping("/")
    public ResponseResult register(@RequestBody RegisterDTO registerDTO) {


        if (userService.getUserByPhone(registerDTO.getPhoneNumber()).getData() != null) {
            return new ResponseResult(500, "手机号已注册", null);
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

        LoginDTO loginDTO = new LoginDTO(account2, user);

        return new ResponseResult(loginDTO);
    }

    @PostMapping("/addMaster")
    public ResponseResult register(@RequestBody AddMasterDTO addMasterDTO, HttpServletRequest request) {

        HttpSession session = request.getSession();
        Master masterX = (Master) session.getAttribute("master");
        if (masterX == null || masterX.getIsCompanyMaster() != 1)
            return new ResponseResult(500, "权限不足", null);
        Master master = masterService.getMasterByName(addMasterDTO.getName());
        if (master != null) {
            return new ResponseResult(501, "用户名已注册", null);
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

        LoginDTO loginDTO = new LoginDTO(account2, master);

        return new ResponseResult(loginDTO);
    }

    @GetMapping("/CompanyCity")
    public ResponseResult getCompanyCity() {
        CompanyCityDTO companyCitys = new CompanyCityDTO();
        List<String> list = companyRepository.findCities();
        return new ResponseResult(list);
    }

    @PostMapping("/Companies")
    public ResponseResult getCompaniesBycity(@RequestBody String cityName) {
        //CompanyCityDTO companyCitys = new CompanyCityDTO();
        List<Company> list = companyRepository.findCompaniesByCity(cityName);
        return new ResponseResult(list);
    }

    @PostMapping("/user")
    @ApiOperation("注册普通用户接口")
    public ResponseResult registerUser(HttpServletRequest request, @RequestParam(value = "idCardFrontImg", required = false) MultipartFile idCardFrontImg, @RequestParam(value = "idCardBackImg", required = false) MultipartFile idCardBackImg, @RequestParam(value = "drivingLicenseFrontImg", required = false) MultipartFile drivingLicenseFrontImg, @RequestParam(value = "drivingLicenseBackImg", required = false) MultipartFile drivingLicenseBackImg, @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                                       @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password, @RequestParam(value = "companyId") Long companyId, @RequestParam(value = "name") String name, @RequestParam(value = "gender") int gender, @RequestParam(value = "idCardNumber") String idCardNumber, @RequestParam(value = "driverLicenseNumber") String driverLicenseNumber, @RequestParam(value = "phoneNumber") String phoneNumber, @RequestParam(value = "workNumber") Long workNumber) throws IOException {
        //校验用户名或电话号码是否已存在
        if (userRepository.findByUserNameAndCheckStatusAndCompanyId(userName, 0, companyId) != null)
            return new ResponseResult(501, "用户名已存在");
        if (userRepository.findByPhoneNumberAndCheckStatusAndCompanyId(phoneNumber, 0, companyId) != null)
            return new ResponseResult(502, "电话号码已注册");

        Account account = new Account(password, UUID.randomUUID().toString(), 0);
        MultipartFile[] files = {idCardFrontImg, idCardBackImg, drivingLicenseFrontImg, drivingLicenseBackImg, avatar};
        String[] uuid = new String[5];
        //上传图片
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file == null) continue;
            Map<String, String> map = uploadService.upfile(file, request);
            if (map.get("code").equals("500"))
                return new ResponseResult(500, map.get("msg"));
            uuid[i] = map.get("uuid");
        }
        accountRepository.save(account);
        User user = new User(userName, account.getId(), workNumber, name, gender, phoneNumber, companyId, idCardNumber, driverLicenseNumber, 0, uuid);//checkStatus=0 未审核
        userRepository.save(user);
        return new ResponseResult();
    }

    @ApiOperation("车辆注册接口")
    @PostMapping("/car")
    public ResponseResult registerCar(HttpServletRequest request, @RequestParam("license") String license, @RequestParam("band") String band, @RequestParam("type") String type, @RequestParam("isPublic") int isPublic, @RequestParam("starTime") String starTime, @RequestParam("endTime") String endTime, @RequestParam("displacement") double displacement, @RequestParam("userId")Long userId,
                                      @RequestParam(value = "picture", required = false) MultipartFile picture, @RequestParam(value = "drivingLicenseImg", required = false) MultipartFile drivingLicenseUrl) throws IOException {
        // 验证汽车牌照是否已存在
        if(carRepository.findByLicense(license)!=null){
            return new ResponseResult(501,"此牌照车辆已存在");
        }

        MultipartFile[] files = {picture,drivingLicenseUrl};
        String[] uuid = new String[2];
        //上传图片
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file == null) continue;
            Map<String, String> map = uploadService.upfile(file, request);
            if (map.get("code").equals("500"))
                return new ResponseResult(500, map.get("msg"));
            uuid[i] = map.get("uuid");
        }
        Car car=new Car(license,uuid[0],uuid[1],band,type,userId,isPublic,1,starTime,endTime,displacement,0);
        carRepository.save(car);
        return new ResponseResult(car);
    }
}
