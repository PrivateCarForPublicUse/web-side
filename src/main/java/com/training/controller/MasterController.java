package com.training.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.prism.impl.ps.BaseShaderContext;
import com.training.domain.Master;
import com.training.domain.User;
import com.training.dto.AuditUserDTO;
import com.training.dto.DeleteMasterDTO;
import com.training.response.ResponseResult;
import com.training.service.CarService;
import com.training.service.MasterService;
import com.training.service.RouteService;
import com.training.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Api(value="/Master",tags="用于测试管理员相关接口")
@RequestMapping("/Master")
@RestController
public class MasterController {
    @Autowired
    CarService carService;
    @Autowired
    RouteService routeService;
    @Autowired
    MasterService masterService;
    @Autowired
    UserService userService;
    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="管理员不存在"),
            @ApiResponse(code=501,message="id不存在"),
            @ApiResponse(code=502,message="name不存在"),
            @ApiResponse(code=503,message="插入失败"),
            @ApiResponse(code=504,message="删除失败"),
            @ApiResponse(code=505,message="更新失败"),
            @ApiResponse(code=506,message="密码错误")
    })


    @ApiOperation("查询所有管理员的接口")
    @GetMapping("/")
    public ResponseResult findAllMasters() {
        return masterService.findAllMasters();
    }

    @ApiOperation("按名字查询管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "管理员名")
    })
    @GetMapping("/name")
    public ResponseResult findMastersByName(@RequestParam("name") String name) {
        return masterService.findMastersByName(name);
    }

    @ApiOperation("按id查询管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id")
    })
    @GetMapping("/id")
    public ResponseResult findMasterById(@RequestParam("id") Long id) {
        return masterService.findMasterById(id);
    }

    @ApiOperation("增加一个新管理员")
    @PostMapping("/add")
    public ResponseResult saveMaster(@RequestBody Master master) {
        masterService.saveMaster(master);
        System.out.println(master.getCompanyId());
        return masterService.saveMaster(master);
    }

    @ApiOperation("根据id删除一个管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id")
    })
    @DeleteMapping("/del")
    public ResponseResult deleteMaster(@RequestParam("id")Long id) {
        masterService.deleteMasterById(id);
        return new ResponseResult("SUCCESS");
    }

    @ApiOperation("更新管理员")
    @PostMapping("/update")
    public ResponseResult updateUser(@RequestBody Master master) {
        return masterService.updateMaster(master);
    }

    @ApiOperation("根据id查询并更新管理员名字")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id"),
            @ApiImplicitParam(name = "name", value = "修改后的管理员名字")
    })
    @PostMapping("/update_name")
    public ResponseResult updateUser(@RequestParam("id")Long id, @RequestParam("name")String name) {
        return masterService.updateNameOfMastersById(id, name);
    }

    @ApiOperation("管理员审核申请用车")
    @PostMapping("/reviewUseCar")
    public ResponseResult reviewUseCar(@RequestBody String body){
        JSONObject json = JSON.parseObject(body);
        return routeService.updateStatusOfRouteById(json.getLong("routeId"),json.getInteger("status"));
    }

    @ApiOperation("管理员获取需要审核的相关信息")
    @GetMapping("/audit-info")
    public ResponseResult getAudit(){
        return masterService.getAuditNum();
    }

//    @ApiOperation("管理员登录接口")
//    @PostMapping("/login")
//    public ResponseResult loginMaster(@RequestBody Master master){
//        return masterService.loginByMasterName(master.getMasterName(),master.getPassword());
//    }

    @ApiOperation("管理员获取需要审核的人员信息")
    @GetMapping("/audit-user-info")
    public ResponseResult getAuditUsers(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");
        if(master==null) return new ResponseResult(500,"权限不足",null);
        return masterService.getAuditUser(master);
    }

    @ApiOperation("管理员同意或驳回人员申请")
    @PostMapping("/audit-user-info")
    public ResponseResult AuditUser(@RequestBody AuditUserDTO auditUserDTO , HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");
        User user = (User)userService.getUserById(auditUserDTO.getUserId()).getData();
        if(user ==null) return new ResponseResult(500,"用户不存在",null);
        else if(master==null) return new ResponseResult(500,"权限不足",null);
        else if(!master.getCompanyId().equals(user.getCompanyId())) return new ResponseResult(500,"公司不同",null);
        return masterService.AuditUser(auditUserDTO);
    }


    @ApiOperation("查询所有本公司管理员的接口")
    @GetMapping("/masterList")
    public ResponseResult findAllMastersByCompanyId(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");
        if(master==null) return new ResponseResult(500,"权限不足",null);
        return masterService.findAllMastersByCompanyId(master.getCompanyId());
    }


    @ApiOperation("删除管理员的接口")
    @PostMapping("/deleteMaster")
    public ResponseResult deleteMaster(@RequestBody DeleteMasterDTO deleteMasterDTO, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");

        Master master1 = (Master) masterService.findMasterById(deleteMasterDTO.getMasterId()).getData();

        if(master==null||master.getIsCompanyMaster()!=1||!master1.getCompanyId().equals(master.getCompanyId())) return new ResponseResult(500,"权限不足",null);
        return masterService.deleteMasterById(deleteMasterDTO.getMasterId());
    }

    @ApiOperation("实时监控")
    @GetMapping("/monitor")
    public ResponseResult realTimeMonitoring(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");
        if(master ==null)
            return new ResponseResult(500,"权限不足!",null);
        return masterService.findUsersAndCars(master.getCompanyId());
    }

    @ApiOperation("查询私车/公车数量")
    @GetMapping("/isPublic")
    public ResponseResult findCarByIsPublicAndCompanyId(HttpServletRequest httpServletRequest,int isPublic){
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");
        if(master ==null)
            return new ResponseResult(500,"权限不足!",null);
        return carService.findCarByIsPublicAndCompanyId(isPublic,master.getCompanyId());
    }

    @ApiOperation("查询员工数量")
    @GetMapping("/findUser")
    public ResponseResult getUsersByCompanyId(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession();
        Master master = (Master) session.getAttribute("master");
        if(master ==null)
            return new ResponseResult(500,"权限不足!",null);
        return userService.getUsersByCompanyId(master.getCompanyId());
    }
}
