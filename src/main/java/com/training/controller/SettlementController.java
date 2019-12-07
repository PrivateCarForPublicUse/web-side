package com.training.controller;


import com.training.domain.Settlement;
import com.training.response.ResponseResult;
import com.training.service.SettlementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value="/Settlement",tags="用于测试报销表相关接口")
@Controller
@RequestMapping("/Settlement")
@RestController
public class SettlementController {
    @Autowired
    SettlementService settlementService;

    @ApiOperation("查询报销表所有信息")
    @GetMapping("/")
    public ResponseResult findAllSettlement() {
        return settlementService.findAllSettlement();
    }

    @ApiOperation("根据报销id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "报销表id")
    })
    @GetMapping("/id")
    public ResponseResult findSettlementById(@RequestParam("id")Long id) {
        return settlementService.findSettlementById(id);
    }

    @ApiOperation("根据行程id查询报销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "routeid", value = "行程id")
    })
    @GetMapping("/routeid")
    public ResponseResult findSettlementByRouteId(@RequestParam("routeid")Long routeid) {
        return settlementService.findSettlementByRouteId(routeid);
    }

    @ApiOperation("根据段行程id查询报销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "secrouteid", value = "段行程id")
    })
    @GetMapping("/secrouteid")
    public ResponseResult findSettlementBySecRouteId(@RequestParam("secrouteid")Long secrouteid) {
        return settlementService.findSettlementBySecRouteId(secrouteid);
    }

    @ApiOperation("根据员工id查询报销")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "员工id")
    })
    @GetMapping("/employeeId")
    public ResponseResult findSettlementByEmployeeId(@RequestParam("employeeId")Long employeeId) {
        return settlementService.findSettlementByEmployeeId(employeeId);
    }

    @ApiOperation("新增报销表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "settlement", value = "新增的报销")
    })
    @GetMapping("/save")
    public ResponseResult saveSettlement(Settlement settlement) {
        return settlementService.saveSettlement(settlement);
    }

    @ApiOperation("更新报销表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "settlement", value = "更新的报销")
    })
    @GetMapping("/update")
    public ResponseResult updateSettlement(Settlement settlement) {
        return settlementService.updateSettlement(settlement);
    }
}
