package com.training.Interceptor;

import com.training.domain.Account;
import com.training.repository.AccountRepository;
import com.training.service.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class SessionInterception implements HandlerInterceptor {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Cookie[] cookies = request.getCookies();
//        if(cookies!=null&&cookies.length!=0)
//            for (Cookie cookie : cookies) {
//                if(cookie.getName().equals("token")){
//                    String token = cookie.getValue();
//                    Account account = accountRepository.findByToken(token);
//                    if(account != null){
//                        request.getSession().setAttribute("account",account);
//                    }
//                    break;
//                }
//            }
        return true;
    }

}
