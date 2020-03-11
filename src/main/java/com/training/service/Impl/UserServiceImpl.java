package com.training.service.Impl;


import com.training.domain.Account;
import com.training.domain.User;
import com.training.model.ChgPwdModel;
import com.training.repository.AccountRepository;
import com.training.repository.UserRepository;
import com.training.response.ResponseResult;
import com.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public ResponseResult getUsers(){
        return new ResponseResult(userRepository.findAll());
    }

    @Override
    public ResponseResult getUserById(Long id) {
        return new ResponseResult(userRepository.findById(id).get());
    }

    @Override
    public ResponseResult save(User user){
        return new ResponseResult(userRepository.save(user));
    }

    @Override
    public ResponseResult update(User user) {
        Optional<User> optional = userRepository.findById(user.getId());

        if(optional.isPresent()==false){
            return new ResponseResult(500,"找不到用户",null);
        }

        User user1 =optional.get();
        user.setPhoneNumber(user1.getPhoneNumber());
        user.setAccountId(user1.getAccountId());
        user.setUserName(user1.getUserName());
        //以上三条信息无法更新！需要走其它通道！
        return new ResponseResult(userRepository.save(user));
    }

    @Override
    public ResponseResult delete(Long id){
        User user = userRepository.findById(id).get();
        userRepository.deleteById(id);
        return new ResponseResult(user);
    }

    @Override
    public ResponseResult getUserByPhone(String s) {
        User user = userRepository.findByPhoneNumber(s);
        return new ResponseResult(user);
    }

    @Override
    public ResponseResult getUserByAccount(Long id) {
        User user = userRepository.findByAccount(id);
        return new ResponseResult(user);
    }

    @Override
    public ResponseResult getUsersByCheckStatus(int status) {
        List<User> users = userRepository.getUsersByCheckStatus(status);
        return new ResponseResult(users);
    }

    @Override
    public ResponseResult updateFDUser(User user){
        return new ResponseResult(userRepository.save(user));
    }

    @Override
    public ResponseResult getUsersByCompanyId(Long companyId){
        List<User> users = userRepository.getUserByCompanyId(companyId);
        return new ResponseResult(users);
    }

    @Override
    public ResponseResult changePassword(User user,ChgPwdModel chgPwdModel){
        Account account = accountRepository.getOne(user.getAccountId());
        if(!chgPwdModel.getP1().equals(account.getPassword()))
            return new ResponseResult(501,"密码错误");
        if(!chgPwdModel.getP2().equals(chgPwdModel.getP3()))
            return new ResponseResult(502,"密码重复输入错误");
        account.setPassword(chgPwdModel.getP2());
        accountRepository.save(account);
        return new ResponseResult();
    }
}
