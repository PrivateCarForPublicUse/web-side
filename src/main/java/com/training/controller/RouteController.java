package com.training.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.training.domain.*;
import com.training.dto.ApplyCarDTO;
import com.training.response.ResponseResult;
import com.training.service.RouteService;
import com.training.service.SecRouteService;
import com.training.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//涉及到参数传递的目前用不了，得等前端页面做出来
@Api(value="/Route",tags="用于测试路程表相关接口")
@RequestMapping("/Route")
@RestController
public class RouteController {

    @Autowired
    UserService userService;
    @Autowired
    RouteService routeService;
    @Autowired
    SecRouteService secRouteService;

    @ApiResponses({
            @ApiResponse(code=200,message="ok"),
            @ApiResponse(code=500,message="行程不存在"),
            @ApiResponse(code=501,message="id不存在"),
            @ApiResponse(code=502,message="carid不存在"),
            @ApiResponse(code=503,message="userId不存在"),
            @ApiResponse(code=504,message="插入失败"),
            @ApiResponse(code=505,message="更新失败")
    })
    @ApiOperation("查询所有行程表的接口")
    @GetMapping("/")
    public ResponseResult findAllRoute() {
        return routeService.findAllRoute();
    }

    @ApiOperation("按id查询行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "行程表id")
    })
    @GetMapping("/id")
    public ResponseResult findRouteById(@RequestParam("id")Long id) {
        return routeService.findRouteById(id);
    }

    @ApiOperation("按carid查询行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "carid", value = "车辆id")
    })
    @GetMapping("/carid")
    public ResponseResult findRouteByCarId(@RequestParam("carid")Long carid) {
        return routeService.findRouteByCarId(carid);
    }

    @ApiOperation("按userId查询行程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "员工id")
    })
    @GetMapping("/userId")
    public ResponseResult findRouteByUserId(@RequestParam("userId")Long userId) {
        return routeService.findRouteByUserId(userId);
    }

    @ApiOperation("保存行程")
    @PostMapping("/save")
    public ResponseResult saveRoute(@RequestBody Route route) {
        return routeService.saveRoute(route);
    }

    @ApiOperation("更新行程")
    @PostMapping("/update")
    public ResponseResult updateRoute(@RequestBody Route route) {
        return routeService.updateRoute(route);
    }

    @ApiOperation("更新行程状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "行程表id"),
            @ApiImplicitParam(name = "st", value = "行程状态改变值")
    })
    @PostMapping("/update_status")
    public ResponseResult updateStatusOfRouteById(@RequestParam("id")Long id, @RequestParam("st")int st) {
        return routeService.updateStatusOfRouteById(id, st);
    }

    @ApiOperation("申请用车")
    @PostMapping("/applyCar")
    public ResponseResult applyCar(@RequestBody ApplyCarDTO applyCarDTO, HttpServletRequest request) {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Long id=user.getId();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Route route = new Route(applyCarDTO.getStartTime(),applyCarDTO.getEndTime(),applyCarDTO.getCarId(),id,0,applyCarDTO.getReason(),0,df.format(new Date()));
        ResponseResult r = routeService.saveRoute(route);
        if (r.getCode() != 200)
            return r;
        Route route1 = (Route) r.getData();
        int i = 0,j = 0;
        while (i < applyCarDTO.getNames().size()){
            SecRoute secRoute = new SecRoute(route1.getId(),applyCarDTO.getNames().get(i),applyCarDTO.getNames().get(i+1),applyCarDTO.getLats().get(j).getLongitude(),applyCarDTO.getLats().get(j).getLatitude(), applyCarDTO.getLats().get(j+1).getLongitude(),applyCarDTO.getLats().get(j+1).getLatitude());
            i += 2;
            j += 2;
            secRouteService.saveSecRoute(secRoute);
        }
        return r;
    }


    @ApiOperation("根据审核状态和管理员Id返回路程信息，返回包含用户、车辆、段路程")
    @ApiImplicitParams({
            @ApiImplicitParam(name="status",value="审核状态（-1 审核不通过；0 未审核；1 审核通过；2 行程中；3 已完成；4 已取消）"),
            @ApiImplicitParam(name = "masterId", value = "管理员ID")
    })
    @GetMapping("/status")
    public ResponseResult findRoutesByStatus(@RequestParam("status")int status,@RequestParam("masterId")Long masterId){
        return routeService.findRoutesByStatus(status,masterId);
    }

    @ApiOperation("获取包含所有信息的所有路程")
    @GetMapping("/fd")
    public ResponseResult findFDRoutes(){
        return routeService.findFDRoutes();
    }

    @ApiOperation("根据报销状态返回包含所有详细数据的路程")
    @ApiImplicitParam(name="is",value="是否报销")
    @GetMapping("/is_reimburse")
    public ResponseResult findFDRoutesByIsReimburse(@RequestParam("is")int is){return routeService.findFDRoutesByIsReimburse(is);}

    @ApiOperation("根据userid返回总路程，花费信息")
    @PostMapping("/total")
    public ResponseResult findData(HttpServletRequest request){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Long id=user.getId();
        return routeService.findDataByUserIdAndStatus(id);
    }


    @ApiOperation("开始行程")
    @PostMapping("/start")
    public ResponseResult startRoute(@RequestBody String body,HttpServletRequest request){
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Long id=user.getId();
        JSONObject json = JSON.parseObject(body);
        Long routeId = json.getLong("routeId"),secRouteId = json.getLong("secRouteId"),tid = json.getLong("tid");
        String trid = json.getString("trid");
        User user1 = (User) userService.getUserById(id).getData();
        user1.setTid(tid);
        userService.update(user1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return routeService.startRoute(id,routeId,secRouteId,trid,df.format(new Date()));
    }

    @ApiOperation("开始行程")
    @PostMapping("/stop")
    public ResponseResult stopRoute(@RequestBody String body,HttpServletRequest request) throws ParseException {
        HttpSession session=request.getSession();
        User user= (User) session.getAttribute("user");
        Long id=user.getId();
        JSONObject json = JSON.parseObject(body);
        Long routeId = json.getLong("routeId"),secRouteId = json.getLong("secRouteId");
        Double actualDistance = json.getDouble("actualDistance"),plannedDistance = json.getDouble("plannedDistance");
        return routeService.stopRoute(id,routeId,secRouteId,plannedDistance,actualDistance);
    }

    /*
    public void deleteRoute(Route route) {
        routeService.deleteRoute(route);
    }

    public void deleteRouteById(Long id) {
        routeService.deleteRouteById(id);
    }

    public void deleteRouteByCarId(Long carid) {
        routeService.deleteRouteByCarId(carid);
    }

    public void deleteRouteByEmployeeId(Long employeeId) {
        routeService.deleteRouteByEmployeeId(employeeId);
    }
     */
}
