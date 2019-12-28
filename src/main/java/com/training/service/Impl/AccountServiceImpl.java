package com.training.service.Impl;

import com.training.domain.Account;
import com.training.domain.Master;
import com.training.domain.User;
import com.training.dto.LoginByMasterNameDTO;
import com.training.dto.LoginByUserNameDTO;
import com.training.dto.LoginDTO;
import com.training.repository.AccountRepository;
import com.training.repository.MasterRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MasterRepository masterRepository;

    @Override
    public ResponseResult getAccounts() {
        return new ResponseResult(accountRepository.findAll());
    }

    @Override
    public ResponseResult getAccountById(Long id) {
        return new ResponseResult(accountRepository.findById(id).get());
    }

    @Override
    public ResponseResult getAccountByToken(String token) {
        return new ResponseResult(accountRepository.findByToken(token));
    }

    @Override
    public ResponseResult save(Account user) {
        user.setToken(UUID.randomUUID().toString());
        return new ResponseResult(accountRepository.save(user));
    }

    @Override
    public ResponseResult update(Account user) {
        return new ResponseResult(accountRepository.save(user));
    }

    @Override
    public ResponseResult delete(Long id) {
        Account account= accountRepository.findById(id).get();
        accountRepository.deleteById(id);
        return new ResponseResult(account);
    }

    public LoginDTO loginByUserName(LoginByUserNameDTO loginByUserNameDTO){
        User user = userRepository.getUserByUserName(loginByUserNameDTO.getName());
        Account account = accountRepository.findById(user.getAccountId()).get();
        if(loginByUserNameDTO.getPassword().equals(account.getPassword())){
            return new LoginDTO(account,user);
        }
        else return null;
    }

    @Override
    public ResponseResult getMasterByAccountId(Long id) {
        Master master = masterRepository.findMasterByACountId(id);
        return new ResponseResult(master);
    }

    @Override
    public LoginDTO loginByMasterName(LoginByMasterNameDTO loginByMasterNameDTO) {
        Master master = masterRepository.getMasterByMasterName(loginByMasterNameDTO.getMasterName());
        Account account = accountRepository.findById(master.getAccountId()).get();
        if(loginByMasterNameDTO.getPassword().equals(account.getPassword())){
            return new LoginDTO(account,master);
        }
        else return null;
    }

}
