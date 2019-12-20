package com.training.controller;


import com.training.domain.Account;
import com.training.domain.User;
import com.training.dto.LoginDTO;
import com.training.dto.RegisterDTO;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import com.training.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="/register",tags="注册接口")
@RequestMapping("/register")
@RestController
public class RegisterController {

    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;

    @PostMapping("/")
    public ResponseResult register(@RequestBody RegisterDTO registerDTO){


        Account account = new Account(); account.setPassword(registerDTO.getPassword());
        Account account2 = (Account) accountService.save(account).getData();

        User user = new User();
        user.setAccountId(account2.getId());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        userService.save(user);

        LoginDTO loginDTO = new LoginDTO(account2,user);

        return new ResponseResult(loginDTO);
    }
}
