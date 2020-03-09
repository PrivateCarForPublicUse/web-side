package com.training.controller;

import com.training.domain.Account;
import com.training.domain.CarRate;
import com.training.domain.Master;
import com.training.domain.User;
import com.training.response.ResponseResult;
import com.training.service.CarRateService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * by Huang
 * 对车的评价增删改查
 */
@Api(value = "/carRate", tags = "对车的评价接口")
@RequestMapping("/carRate")
@RestController
public class CarRateController {
    @Autowired
    CarRateService carRateService;

    @ApiResponses({
            @ApiResponse(code = 500, message = "不存在任何评价")
    })
    @ApiOperation("管理员接口：获取本公司所有车辆评价列表")
    @GetMapping("/fd")
    public ResponseResult carRates(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int flag = account.getFlag();
        if (flag == 1) {
            Master master = (Master) session.getAttribute("master");
            Long companyId = master.getCompanyId();
            return carRateService.getCarRates(companyId);
        } else {
            return new ResponseResult(600, "没有权限!");
        }
    }

    @ApiResponses({
            @ApiResponse(code = 501, message = "新建失败")
    })
    @ApiOperation("用户接口：新增评价")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody CarRate carRate) {
        return carRateService.save(carRate);
    }

    @PostMapping("/add-rate")
    @ApiOperation("新增评价，使用当前登录用户作为userId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "新建成功"),
            @ApiResponse(code = 501, message = "用户未登录"),
            @ApiResponse(code = 502, message = "信息未填写完整")
    })
    public ResponseResult addRate(HttpServletRequest request, @RequestBody CarRate carRate) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) return new ResponseResult(501, "用户未登录");
        if(carRate.getRate()==0||carRate.getComment()==null||carRate.getComment().equals(""))
            return new ResponseResult(502,"信息未填写完整");
        carRate.setUserId(user.getId());
        return carRateService.save(carRate);
    }

    @ApiResponses({
            @ApiResponse(code = 502, message = "该用户没有发表过任何评价")
    })
    @ApiOperation("根据评论人id获取其发表的所有对车的评论")
    @ApiImplicitParam(name = "userId", value = "评论人id")
    @GetMapping("/userId/{userId}")
    public ResponseResult findByUserId(@PathVariable("userId") Long userId) {
        return carRateService.findByUserId(userId);
    }

    @ApiResponses({
            @ApiResponse(code = 503, message = "该车未曾收到过评价")
    })
    @ApiOperation("根据车的id获取该车的所有评价")
    @ApiImplicitParam(name = "carId", value = "车的id")
    @GetMapping("/carId/{carId}")
    public ResponseResult findByEvaluateeId(@PathVariable("carId") Long carId) {
        return carRateService.findByCarId(carId);
    }
}
