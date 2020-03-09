package com.training.controller;

import com.training.domain.Account;
import com.training.domain.Master;
import com.training.domain.User;
import com.training.domain.UserRate;
import com.training.response.ResponseResult;
import com.training.service.UserRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * by Huang
 * 对借车人的评价增删改查
 */
@Api(value="/userRate",tags="对借车人评价接口")
@RequestMapping("/userRate")
@RestController
public class UserRateController {
    @Autowired
    UserRateService userRateService;

    @ApiResponses({
            @ApiResponse(code=500,message="不存在任何评价")
    })
    @ApiOperation("管理员接口：获取本公司所有对员工的评价列表")
    @GetMapping("/fd")
    public ResponseResult userRates(HttpServletRequest request){
        HttpSession session=request.getSession();
        Account account= (Account) session.getAttribute("account");
        int flag=account.getFlag();
        if(flag==1){
            Master master= (Master) session.getAttribute("master");
            Long companyId=master.getCompanyId();
            return userRateService.getUserRates(companyId);
        }else{
            return new ResponseResult(600,"没有权限!");
        }
    }

    @ApiResponses({
            @ApiResponse(code=502,message="该用户没有发表过任何评价")
    })
    @ApiOperation("根据评论人id获取其发表的所有对借车人的评论")
    @ApiImplicitParam(name="userId",value = "评论人id")
    @GetMapping("/userId/{userId}")
    public ResponseResult findByUserId(@PathVariable("userId") Long userId){
        return userRateService.findByUserId(userId);
    }

    @ApiResponses({
            @ApiResponse(code=503,message="该用户没有受到过任何评价")
    })
    @ApiOperation("根据被评论人id获取其受到的所有评论")
    @ApiImplicitParam(name="evaluateeId",value = "被评论人id")
    @GetMapping("/evaluateeId/{evaluateeId}")
    public ResponseResult findByEvaluateeId(@PathVariable("evaluateeId") Long evaluateeId){
        return userRateService.findByEvaluateeId(evaluateeId);
    }

    @ApiOperation("根据被评论人id获取其受到的所有评论，没有返回503的版本")
    @ApiImplicitParam(name="evaluateeId",value = "被评论人id")
    @GetMapping("/evaluateeId2/{evaluateeId}")
    public ResponseResult findByEvaluateeId2(@PathVariable("evaluateeId") Long evaluateeId){
        return userRateService.findByEvaluateeId2(evaluateeId);
    }

    @ApiResponses({
            @ApiResponse(code=501,message="新建失败")
    })
    @ApiOperation("新增评价")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody UserRate userRate){
        return userRateService.save(userRate);
    }

    @ApiOperation("根据但当前登录用户新增评价")
    @PostMapping("/add-user")
    public ResponseResult addUserRate(HttpServletRequest request, @RequestBody UserRate userRate){
        User user=(User)request.getSession().getAttribute("user");
        if(user==null)return new ResponseResult(501,"用户未登录");
        userRate.setUserId(user.getId());
        return userRateService.save(userRate);
    }

}
