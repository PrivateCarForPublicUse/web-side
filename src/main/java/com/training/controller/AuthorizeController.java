package com.training.controller;


import com.training.domain.Account;
import com.training.domain.User;
import com.training.dto.LoginByPhoneDTO;
import com.training.dto.LoginByUserNameDTO;
import com.training.dto.LoginDTO;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import com.training.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="/authorize",tags="验证接口")
@RequestMapping("/authorize")
@RestController
public class AuthorizeController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;

    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="密码错误"),
            @ApiResponse(code=501,message="令牌失效"),
    })

    @PostMapping("/login")
    public ResponseResult login(@RequestBody Account account){
        ResponseResult accountRes = accountService.getAccountById(account.getId());
        Account account1 = (Account)accountRes.getData();

        if(accountRes.getData()!=null&&account1.getPassword().equals(account.getPassword())){

            ResponseResult userRes=userService.getUserByAccount(account1.getId());
            User user = (User) userRes.getData();

            LoginDTO loginDTO = new LoginDTO(account1,user);

            return new ResponseResult(loginDTO);
        }

        else return new ResponseResult(500,"账号密码错误",null);
    }

    @PostMapping("/login/token")
    public ResponseResult loginByToken(@RequestBody Account account){
        ResponseResult account2 = accountService.getAccountByToken(account.getToken());
        if(account2.getData()!=null){
            return account2;
        }
        else return new ResponseResult(501,"令牌无效",null);
    }
    @PostMapping("/login/phone")
    public ResponseResult loginByPhoneNumber(@RequestBody LoginByPhoneDTO loginByPhoneDTO){

        User user = (User)userService.getUserByPhone(loginByPhoneDTO.getPhoneNumber()).getData();


        Account account = new Account();
        account.setPassword(loginByPhoneDTO.getPassword());

        account.setId(user.getAccountId());
        return this.login(account);


    }
    @PostMapping("/login/username")
    public ResponseResult loginByUserName(@RequestBody LoginByUserNameDTO loginByUserNameDTO){


        LoginDTO loginDTO = accountService.loginByUserName(loginByUserNameDTO);
        if(loginDTO==null)
            return new ResponseResult(500,"账号密码错误",null);
        else return new ResponseResult(loginDTO);

    }
}
